package com.iscas.biz.calculation.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import net.arnx.wmf2svg.gdi.svg.SvgGdi;
import net.arnx.wmf2svg.gdi.wmf.WmfParser;

/**
 * @author yichuan@iscas.ac.cn
 * @version 1.0
 * @date 2023/6/14 17:03
 */
public class WmfToSvgUtils {
    public static void main(String[] args) {
        // wmf 本地路径
        String wmfPath = "D:\\WeChat\\WeChat Files\\wxid_illgpkm61nbz21\\FileStorage\\File\\2023-06\\总强度计算剖面.wmf";
        // svg 本地临时路径
        String svgPath = "ceshi.svg";
        // 是否替换符号字体
        boolean replaceSymbolFont = false;
        try {
            InputStream in = new FileInputStream(wmfPath);
            WmfParser parser = new WmfParser();
            final SvgGdi gdi = new SvgGdi(false);
            gdi.setReplaceSymbolFont(replaceSymbolFont);
            //todo 设置SvgPen 的width为10
            parser.parse(in, gdi);
            OutputStream out = null;
            try {
                out = new FileOutputStream(svgPath);
                if (svgPath.endsWith(".svgz")) {
                    out = new GZIPOutputStream(out);
                }
                gdi.write(out);
            } finally {
                if (out != null) out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
