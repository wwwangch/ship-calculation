package com.iscas.common.tools.core.io.file;

/**
 * 文件类型枚举
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/8/6 8:48
 * @since jdk1.8
 */

public enum FileTypeEnum {
    /**
     * 未知类型
     */
    UNKOWN("unkown", "FFFFFFFF"),

    /**
     * jpg图片
     */
    JPEG("jpg", "FFD8FF"),

    /**
     * png图片
     */
    PNG("png", "89504E47"),

    /**
     * gif图片
     */
    GIF("gif", "47494638"),

    /**
     * tif图像
     */
    TIFF("tif", "49492A00"),

    /**
     * bmp图像
     */
    WINDOWS_BITMAP("bmp", "424D"),

    /**
     * CAD
     */
    CAD("dwg", "41433130"),

    /**
     * ps图像
     */
    ADOBE_PHOTOSHOP("psd", "38425053"),

    /**
     * RTF
     */
    RICH_TEXT_FORMAT("rtf", "7B5C727466"),

    /**
     * xml
     */
    XML("xml", "3C3F786D6C"),

    /**
     * HTML
     */
    HTML("html", "68746D6C3E"),

    /**
     * email
     */
    EMAIL("eml", "44656C69766572792D646174653A"),

    /**
     * outlook express
     */
    OUTLOOK_EXPRESS("dbx", "CFAD12FEC5FD746F"),

    /**
     * outlook
     */
    OUTLOOK("pst", "2142444E"),

    /**
     * word excel
     */
    MS_WORD_EXCEL("xls.or.doc", "D0CF11E0"),

    /**
     * ACCESS
     */
    MS_ACCESS("mdb", "5374616E64617264204A"),

    /**
     * wpd
     */
    WORDPERFECT("wpd", "FF575043"),

    /**
     * EPS.OR.PS
     */
    POSTSCRIPT("eps.or.ps", "252150532D41646F6265"),

    /**
     * pdf
     */
    ADOBE_ACROBAT("pdf", "255044462D312E"),

    /**
     * QDF
     */
    QUICKEN("qdf", "AC9EBD8F"),

    /**
     * PWL
     */
    WINDOWS_PASSWORD("pwl", "E3828596"),

    /**
     * zip
     */
    ZIP("zip", "504B0304"),

    /**
     * rar
     */
    RAR("rar", "52617221"),

    /**
     * mav
     */
    WAVE("wav", "57415645"),

    /**
     * avi
     */
    AVI("avi", "41564920"),

    /**
     * real audio
     */
    REAL_AUDIO("ram", "2E7261FD"),

    /**
     * REAL MEDIA
     */
    REAL_MEDIA("rm", "2E524D46"),

    /**
     * MPG
     */
    MPEG("mpg", "000001BA"),

    /**
     * MPG
     */
    MPEG2("mpg", "000001B3"),

    /**
     * MOV
     */
    QUICKTIME("mov", "6D6F6F76"),

    /**
     * ASF
     */
    WINDOWS_MEDIA("asf", "3026B2758E66CF11"),

    /**
     * MID
     */
    MIDI("mid", "4D546864");

    private String name;
    private String prefix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    FileTypeEnum(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }
}
