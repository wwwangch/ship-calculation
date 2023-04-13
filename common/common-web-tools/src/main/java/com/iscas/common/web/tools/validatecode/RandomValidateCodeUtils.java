package com.iscas.common.web.tools.validatecode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;

/**
 * 验证码校验工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "SameParameterValue", "unused"})
@Deprecated
public class RandomValidateCodeUtils {
    private SecureRandom random = new SecureRandom();
    /**
     * 随机产生的字符串
     */
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 图片宽
     */
    private int width = 80;
    /**
     * 图片高
     */
    private int height = 26;
    /**
     * 干扰线数量
     */
    private int lineSize = 40;
    /**
     * 随机产生字符数量
     */
    private int stringNum = 4;

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    /**
     * 获得颜色
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    /**
     * 生成随机图片
     */
    @SuppressWarnings({"AlibabaRemoveCommentedCode", "MagicConstant"})
    public void getRandcode(HttpServletRequest request, HttpServletResponse response, String key) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        //1：将随机生成的验证码放入Cookie中
//        Cookie cookie = new Cookie(key,randomString);
//        response.addCookie(cookie);
        //2：将随机生成的验证码放入session中
        request.getSession().setAttribute(key, randomString);
        //System.out.println("*************" + randomString);
        //总结：这两种方式都不是很好，
        //（1）：使用cookie的方式，将验证码发送到前台浏览器，不安全！不建议使用。
        //（2）：使用session的方式，虽然能解决验证码不发送到浏览器，安全性较高了，但是如果用户量太大，这样的存储方式会对服务器造成压力，影响服务器的性能。不建议使用。
        //这里暂时实现用这种方式，好的办法是，在项目中使用的缓存，将生成的验证码存放到缓存中，设置失效时间，这样既可以实现安全性也能减轻服务器的压力。
        g.dispose();
        Exception exception = null;
        try {
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(image, "png", tmp);
            tmp.close();
            Integer contentLength = tmp.size();
            response.setHeader("content-length", contentLength + "");
            // 将内存中的图片通过流动形式输出到客户端
            response.getOutputStream().write(tmp.toByteArray());
        } catch (Exception e) {
            exception = e;
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (Exception e2) {
                if (exception != null) {
                    exception.addSuppressed(e2);
                }
                e2.printStackTrace();
            }
        }
    }
}
