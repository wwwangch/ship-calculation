package com.iscas.common.tools.hutool.core.img;

import cn.hutool.core.img.Img;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * hutools图像操作
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/15 8:28
 * @since jdk1.8
 */
public class ImgTests {

    /**底部要被切除的高度*/
    private static final int CUT_HEIGHT = 20;

    /**存储图片的文件夹的路径*/
    private static final String PIC_PATH = "d:/tmp/111";

    @Test
    public void testCutImg() throws IOException {
        //获取所有PNG图片和jpg图片，包括子文件夹中的
        List<Path> picPaths = Files.walk(Paths.get(PIC_PATH), FileVisitOption.FOLLOW_LINKS)
                .filter(path -> path.toString().endsWith(".png") || path.toString().endsWith(".jpg"))
                .collect(Collectors.toList());

        //遍历所有图片，并从底部切除 CUT_HEIGHT高度的图像
        for (Path picPath : picPaths) {
            //构建BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(picPath.toFile());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            //构建hutools中的Img对象
            Img img = new Img(bufferedImage);
            //切除
            img = img.cut(new Rectangle(0, 0, width, height - CUT_HEIGHT));

            //写回原来的图片文件
            String picFormat = StringUtils.substringAfterLast(picPath.toString(), ".");
            Image img1 = img.getImg();
        }

    }
}
