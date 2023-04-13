package com.iscas.common.tools.jdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integer 静态方法测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/3 20:48
 * @since jdk1.8
 */
public class IntegerTests {
    private String integerStr = "18";
    private String integerStr16 = "12";
    private String integerStr8 = "22";
    private int integer = 18;
    private int integer2 = 19;
    private String systemPropKey = "systemPropKey";

    @BeforeEach
    public void before() {
        System.setProperty(systemPropKey, integerStr);
    }

    @Test
    public void test() {

        //=========valueOf和parseInt的区别是valueOf返回包装类，parseInt返回基本类型============================
        //1、测试valueOf，输入字符串
        Integer i1 = Integer.valueOf(integerStr);
        System.out.println("1、测试valueOf：" + i1);

        //2、测试valueOf，输入int
        Integer i2 = Integer.valueOf(integer);
        System.out.println("2、测试valueOf：" + i2);

        //3、测试valueOf，输入字符串和进制
        Integer i3 = Integer.valueOf(integerStr16, 16);
        System.out.println("3、测试valueOf：" + i3);

        //4、测试parseInt，输入字符串
        int i4 = Integer.parseInt(integerStr);
        System.out.println("4、测试parseInt：" + i4);

        //5、测试parseInt，输入字符串和进制
        int i5 = Integer.parseInt(integerStr16, 16);
        System.out.println("5、测试parseInt：" + i5);

        //6、测试parseInt，输入字符串、字符串起止位置，进制
//        int i6 = Integer.parseInt(integerStr, 0, 2, 10);
//        System.out.println("6、测试parseInt：" + i6);

        //7、bitCount实现的功能是计算一个int类型的数值在二进制下“1”的数量。
        int i7 = Integer.bitCount(integer);
        System.out.println(Integer.toBinaryString(integer));
        System.out.println("7、测试bitCount：" + i7);

        //8、比较两个int,使用compare,前面比后面小为负值，前面比后面大为正值
        int i8 = Integer.compare(integer, integer2);
        System.out.println("8、测试compare：" + i8);

        //9、比较两个int,使用compareUnsigned,无符号数比较。前面比后面小为负值，前面比后面大为正值
        int i9 = Integer.compareUnsigned(integer, -integer2);
        System.out.println("9、测试compareUnsigned：" + i9);

        //10、decode，可以解码十进制、八进制和十六进制
        int i10 = Integer.decode(integerStr);
        System.out.println("10、测试decode-十进制：" + i10);
        System.out.println("10、测试decode-八进制：" + Integer.decode(integerStr8));
        System.out.println("10、测试decode-十六进制：" + Integer.decode(integerStr16));

        //11、返回将第一个参数除以第二个参数的无符号商，其中每个参数和结果被解释为无符号值。
        int i11 = Integer.divideUnsigned(integer, 4);
        System.out.println("11、测试divideUnsigned：" + i11);

        //12、返回byteValue值
        byte i12 = Integer.valueOf(integer).byteValue();
        System.out.println("12、测试byteValue：" + i12);

        //13、返回floatValue值
        float i13 = Integer.valueOf(integer * 10000000).floatValue();
        System.out.println("13、测试floatValue：" + i13);

        //14、返回doubleValue值
        double i14 = Integer.valueOf(integer * 10000000).doubleValue();
        System.out.println("14、测试doubleValue：" + i14);

        //15、返回shortValue值
        double i15 = Integer.valueOf(integer).shortValue();
        System.out.println("15、测试shortValue：" + i15);

        //16、返回longValue值
        double i16 = Integer.valueOf(integer * 10000000).longValue();
        System.out.println("16、测试longValue：" + i16);

        //17、getInteger,获取系统变量的int值,相当于System.getProperty("xx")
        Integer i17 = Integer.getInteger(systemPropKey);
        System.out.println("17、测试getInteger：" + i17);

        //18、getInteger,获取系统变量的int值,如果系统变量中不存在，取第二个参数的值
        Integer i18 = Integer.getInteger(systemPropKey + "xxx", integer);
        System.out.println("18、测试getInteger：" + i18);

        //19、highestOneBit 返回小于等于这个数字的一个2的幂次方数
        Integer i19 = Integer.highestOneBit(integer);
        System.out.println("19、测试highestOneBit：" + i19);

        //20、lowestOneBit 传入一个int参数i，返回其二进制最低位1的权值
        Integer i20 = Integer.lowestOneBit(integer);
        System.out.println("20、测试lowestOneBit：" + i20);

        //21、比较两个值的大的值
        Integer i21 = Integer.max(integer, integer2);
        System.out.println("21、测试max：" + i21);

        //22、比较两个值的小的值
        Integer i22 = Integer.min(integer, integer2);
        System.out.println("22、测试min：" + i22);

        //23、返回的最高阶的（“最左边的”）中所指定的二进制补码表示的一个位前述零个比特的数量 int值。
        Integer i23 = Integer.numberOfLeadingZeros(integer);
        System.out.println("23、测试numberOfLeadingZeros：" + i23);

        //24、返回零位以下最低阶（“最右边的”）的数量在指定的二进制补码表示的一个位 int值。
        Integer i24 = Integer.numberOfTrailingZeros(integer);
        System.out.println("24、测试numberOfTrailingZeros：" + i24);

        //25、返回无符号余数，将第一个参数除以秒，其中每个参数和结果被解释为无符号值。
        Integer i25 = Integer.remainderUnsigned(integer, 4);
        System.out.println("25、测试remainderUnsigned：" + i25);

        //26、返回由指定的二进制补码表示反转位的顺序而获得的值 int值。
        Integer i26 = Integer.reverse(integer);
        System.out.println("26、测试reverse：" + i26);

        //27、返回反转指定的二进制补码表示的字节顺序而获得的值 int值。
        Integer i27 = Integer.reverseBytes(integer);
        System.out.println("27、测试reverseBytes：" + i27);

        //28、返回通过旋转指定的二的补码的二进制表示而得到的值 int由位指定数目的左值。
        Integer i28 = Integer.rotateLeft(integer, 2);
        System.out.println("28、测试rotateLeft：" + i28);

        //29、返回通过旋转指定的二的补码的二进制表示而得到的值 int右移位的指定数值。
        Integer i29 = Integer.rotateRight(integer, 2);
        System.out.println("29、测试rotateRight：" + i29);

        //30、返回指定的 int值的 符号函数。
        Integer i30 = Integer.signum(-integer);
        System.out.println("30、测试signum：" + i30);

        //31、计算两个int的值。
        Integer i31 = Integer.sum(integer, integer2);
        System.out.println("31、测试sum：" + i31);

        //32、返回二进制的字符串。
        String i32 = Integer.toBinaryString(integer);
        System.out.println("32、测试toBinaryString：" + i32);

        //33、返回十六进制的字符串。
        String i33 = Integer.toHexString(integer);
        System.out.println("33、测试toHexString：" + i33);

        //34、返回八进制的字符串。
        String i34 = Integer.toOctalString(integer);
        System.out.println("34、测试toOctalString：" + i34);

        //35、转换为无符号的long。
        long i35 = Integer.toUnsignedLong(-integer);
        System.out.println("35、测试toUnsignedLong：" + i35);

        //36、转换为无符号的String。
        String i36 = Integer.toUnsignedString(-integer);
        System.out.println("36、测试toUnsignedString：" + i36);

        //37、转换为无符号的String,可以选进制
        String i37 = Integer.toUnsignedString(-integer, 8);
        System.out.println("37、测试toUnsignedString：" + i37);

    }

}
