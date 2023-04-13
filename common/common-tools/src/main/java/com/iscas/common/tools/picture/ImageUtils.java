package com.iscas.common.tools.picture;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 21:10
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
public class ImageUtils {
    private ImageUtils() {
    }

    /**
     * 将base64转为图片
     * */
    public static void convertBase64ToImage(String base64, OutputStream os) throws IOException {
        Base64.Decoder decoder = Base64.getDecoder();
        // 解密
        byte[] b = decoder.decode(base64);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        os.write(b);
        os.flush();
    }

    public static void cut(int x,int y,int width,int height,String srcpath,String subpath) throws IOException {//裁剪方法
        FileInputStream is=null;
        ImageInputStream iis=null;
        try{
            //读取原始图片
            is=new FileInputStream(srcpath);
            //ImageReader声称能够解码指定格式
            Iterator<ImageReader> it= ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader=it.next();
            //获取图片流
            iis=ImageIO.createImageInputStream(is);
            //将iis标记为true（只向前搜索）意味着包含在输入源中的图像将只按顺序读取
            reader.setInput(iis, true);
            //指定如何在输入时从 Java Image I/O框架的上下文中的流转换一幅图像或一组图像
            ImageReadParam param=reader.getDefaultReadParam();
            //定义空间中的一个区域
            Rectangle rect=new Rectangle(x, y, width, height);
            //提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);
            //读取索引imageIndex指定的对象
            BufferedImage bi=reader.read(0, param);
            //保存新图片
            ImageIO.write(bi, "jpg", new File(subpath));
        }finally{
            if(is!= null) {
                is.close();
            }
            if(iis != null) {
                iis.close();
            }
        }
    }

    public void cutByTemplate2(BufferedImage oriImage,BufferedImage newSrc,BufferedImage newSrc2,int x,int y,int width,int height, int c_a, int c2_b){
        //固定圆半径为5
        int c_r=10;
        //r平方
        double rr=Math.pow(c_r, 2);
        for(int i=0;i<oriImage.getWidth();i++){
            for(int j=0;j<oriImage.getHeight();j++){

                double f=Math.pow((i-c_a), 2)+Math.pow((j- y), 2);

                double f2=Math.pow((i- x), 2)+Math.pow((j-c2_b), 2);

                int rgb=oriImage.getRGB(i,j);
                //在矩形内
                if(i>=x&&i<(x+width) &&j>=y&&j<(y+height) && f2>=rr){
                    //块范围内的值
                    in(newSrc, newSrc2, i, j, rgb);
                }else if(f<=rr){
                    //在圆内
                    in(newSrc, newSrc2, i, j, rgb);
                }else{
                    //剩余位置设置成透明
                    out(newSrc, newSrc2, i, j, rgb);
                }

            }
        }
    }

    private void in(BufferedImage newSrc,BufferedImage newSrc2,int i,int j,int rgb){
        newSrc.setRGB(i, j, rgb);
        //原图设置变灰
        int r = (0xff & rgb);
        int g = (0xff & (rgb >> 8));
        int b = (0xff & (rgb >> 16));
        rgb = r + (g << 8) + (b << 16) + (100 << 24);
        //rgb = r + (g << 8) + (b << 16);
        newSrc2.setRGB(i, j, rgb);
        newSrc.setRGB(i + 1, j + 1, rgb);
    }

    private void out(BufferedImage newSrc, BufferedImage newSrc2, int i, int j, int rgb){
        newSrc.setRGB(i, j, 0x00ffffff);
        newSrc2.setRGB(i, j, rgb);
    }


    public static BufferedImage createDropShadow(BufferedImage image,
                                                 int size, float opacity) {
        int width = image.getWidth() + size * 2;
        int height = image.getHeight() + size * 2;
        BufferedImage mask = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        g2.drawImage(image, size, size, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,
                opacity));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        BufferedImage shadow = createBlurOp(size).filter(mask, null);
        g2 = shadow.createGraphics();
        g2.dispose();
        return shadow;
    }
    private static ConvolveOp createBlurOp(int size) {
        float[] data = new float[size * size];
        float value = 1f / (float) (size * size);
        Arrays.fill(data, value);
        return new ConvolveOp(new Kernel(size, size, data),
                ConvolveOp.EDGE_NO_OP, null);
    }

}
