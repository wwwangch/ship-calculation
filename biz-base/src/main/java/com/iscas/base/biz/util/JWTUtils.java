package com.iscas.base.biz.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iscas.base.biz.autoconfigure.auth.TokenProps;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.service.IAuthCacheService;
import com.iscas.common.tools.core.date.DateRaiseUtils;
import com.iscas.common.tools.core.io.file.ConfigUtils;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidTokenException;
import lombok.Cleanup;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 22:29
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "AlibabaClassNamingShouldBeCamel", "unused"})
public class JWTUtils {
    private JWTUtils() {
    }

    public static final String SECRET = "ISCAS";
    public static final String ISS = "1234567890";

    public enum AlgorithmType {
        /**
         * HMAC256
         */
        HMAC256("hmac256"),

        /**
         * RSA
         */
        RSA("rsa");

        private final String value;

        public String getValue() {
            return value;
        }

        AlgorithmType(String value) {
            this.value = value;
        }

        public static AlgorithmType getEnum(String value) {
            return Arrays.stream(AlgorithmType.values())
                    .filter(type -> type.value.equalsIgnoreCase(value))
                    .findFirst()
                    .orElse(null);
        }

    }


    public static String createToken(String username, int expire) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return createToken(username, expire, AlgorithmType.HMAC256);
    }

    public static String createToken(String userIdAndName, int expire, AlgorithmType type) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Date iatDate = new Date();
        Date expiresDate = DateRaiseUtils.afterOffsetDate(expire * 60 * 1000L);
        Map<String, Object> map = new HashMap<>(2 << 2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = doCreateToken(userIdAndName, iatDate, expiresDate, map, type);
        //将token缓存起来
        CacheUtils.putCache(Constants.AUTH_CACHE, token, iatDate);
//        IAuthCacheService authCacheService = SpringUtils.getApplicationContext().getBean(IAuthCacheService.class);
//        authCacheService.set(token, iatDate, Constants.AUTH_CACHE, expire * 60);

        return token;
    }

    public static Map<String, Claim> verifyToken(String token) throws IOException, ValidTokenException, NoSuchAlgorithmException, InvalidKeySpecException {
        return verifyToken(token, AlgorithmType.HMAC256);
    }

    public static Map<String, Claim> verifyToken(String token, AlgorithmType type) throws IOException, ValidTokenException, NoSuchAlgorithmException, InvalidKeySpecException {
//        IAuthCacheService authCacheService = SpringUtils.getApplicationContext().getBean(IAuthCacheService.class);
//        Object obj = authCacheService.get(token, Constants.AUTH_CACHE);
        Object obj = CacheUtils.getCache(Constants.AUTH_CACHE, token, String.class);
        if (obj == null) {
            throw Exceptions.validTokenException("登录凭证校验失败", "token:" + token + "不存在或已经被注销");
        }
        Algorithm algorithm = getVerifyAlgorithm(type);
        JWTVerifier jwtVerifier = switch (type) {
            case HMAC256 -> JWT.require(algorithm).build();
            case RSA -> JWT.require(algorithm).withIssuer(ISS).build();
            //noinspection UnnecessaryDefault
            default -> throw Exceptions.formatUnsupportedOperationException("不支持的加密算法类型:[{}]", type);
        };

        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (Exception e) {
            throw Exceptions.validTokenException("登录凭证校验失败", "token:" + token + "校验失败", e);
        }
        return decodedJWT.getClaims();
    }

    public static DecodedJWT decodeHMAC256(String token, String secret) throws UnsupportedEncodingException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        return jwtVerifier.verify(token);
    }

    /**
     * 获取当前登录的用户名
     */
    public static String getLoginUsername() {
        String token = AuthUtils.getToken();
        if (token == null) {
            throw Exceptions.authenticationRuntimeException("未携带身份认证信息", "header中未携带 Authorization 或未携带cookie或cookie中无Authorization");
        }
        //如果token不为null,校验token
        String username;
        try {
            Map<String, Claim> clainMap = JWTUtils.verifyToken(token, SpringUtils.getBean(TokenProps.class).getCreatorMode());
            username = clainMap.get("username").asString();
            if (username == null) {
                throw Exceptions.validTokenException("token 校验失败，未获取到用户信息");
            }
        } catch (ValidTokenException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw Exceptions.authenticationRuntimeException("未获取到当前登录的用户信息", e);
        }
        IAuthCacheService authCacheService = SpringUtils.getApplicationContext().getBean(IAuthCacheService.class);

        //修改为支持用户多会话模式
        if (!authCacheService.listContains(Constants.KEY_USER_TOKEN + username, token)) {
            throw Exceptions.authenticationRuntimeException("身份认证信息有误", "token有误或已被注销");
        }

        return username;
    }

    /**
     * 获取RSA算法
     */
    private static Algorithm createRsaAlgorithm(Boolean privateKeyFlag) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPrivateKey privateKey = null;
        RSAPublicKey publicKey = null;
        if (privateKeyFlag == null || privateKeyFlag) {
            @Cleanup InputStream is = ConfigUtils.getInOutConfigStream("/rsakey/pkcs8_private.key");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is.transferTo(baos);
            byte[] keyBytes = baos.toByteArray();
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) kf.generatePrivate(spec);
        } else {
            InputStream is2 = ConfigUtils.getInOutConfigStream("/rsakey/rsa-public-key.pem");
            @Cleanup PemReader pemReader = new PemReader(new InputStreamReader(is2));
            PemObject pemObject = pemReader.readPemObject();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pemObject.getContent());
            KeyFactory kf2 = KeyFactory.getInstance("RSA");
            publicKey = (RSAPublicKey) kf2.generatePublic(pubKeySpec);
        }
        return Algorithm.RSA256(publicKey, privateKey);
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private static String doCreateToken(String userIdAndName, Date iatDate, Date expiresDate, Map<String, Object> map, AlgorithmType type) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Algorithm algorithm;
        String username;
        int userId = -1;
        if (userIdAndName.contains(";")) {
            String[] strs = userIdAndName.split(";");
            userId = Integer.parseInt(strs[0]);
            username = strs[1];
        } else {
            username = userIdAndName;
        }
        return switch (type) {
            case HMAC256 -> JWT.create()
                    .withHeader(map)
                    .withClaim("username", username)
                    .withClaim("userId", userId)
                    .withClaim("date", iatDate)
                    .withExpiresAt(expiresDate)
                    .withIssuedAt(iatDate)
                    .sign(Algorithm.HMAC256(SECRET));
            case RSA -> JWT.create()
                        .withHeader(map)
                        .withIssuer(ISS)
                        .withClaim("username", username)
                        .withClaim("userId", userId)
                        .withClaim("date", iatDate)
                        .withClaim("sub", ISS)
                        .withClaim("iss", ISS)
                        .withExpiresAt(expiresDate)
                        .withIssuedAt(iatDate)
                        .sign(createRsaAlgorithm(true));
            //noinspection UnnecessaryDefault
            default -> throw Exceptions.formatUnsupportedOperationException("不支持的加密算法类型:[{}]", type);
        };
    }

    private static Algorithm getVerifyAlgorithm(AlgorithmType type) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return switch (type) {
            case HMAC256 -> Algorithm.HMAC256(SECRET);
            case RSA -> createRsaAlgorithm(false);
            //noinspection UnnecessaryDefault
            default -> throw Exceptions.formatUnsupportedOperationException("不支持的加密算法类型:[{}]", type);
        };
    }

}
