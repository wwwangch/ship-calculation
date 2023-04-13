package com.iscas.biz.test.jol;

import com.iscas.biz.domain.common.User;
import org.openjdk.jol.info.ClassLayout;

/**
 * 对象的结构包括：对象头、对象体、对齐字节
 * 对象头有三个：Mark Word、Klass Word、数组长度
 *
 * MarkWord的构成如下：
 * ------------------------------------------------------------------------------|-----------|
 *                     Mark Word(64 bits)                                        |  锁状态    |
 * ------------------------------------------------------------------------------|-----------|
 * unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:0 | lock:01 |  正  常    |
 * ------------------------------------------------------------------------------|-----------|
 * thread:54 |    epoch:2    |        unused:1 | age:4 | biased_lock:1 | lock:01 |  偏向锁    |
 * ------------------------------------------------------------------------------|-----------|
 *                     ptr_to_lock_record:62                           | lock:00 |  轻量级锁  |
 * ------------------------------------------------------------------------------|-----------|
 *                 ptr_to_heavyweight_monitor:62                       | lock:11 |  重量级锁   |
 * ------------------------------------------------------------------------------|-----------|
 *                                                                     | lock:11 |  GC标记    |
 * ------------------------------------------------------------------------------|-----------|
 *
 * 对象在处于5中不同的状态时，Mark Word的64表现形式
 * 1、lock。2位，锁状态的标记位，
 * 2、biased_lock。1位。对象是否存在偏向锁标记。lock与biased_lock共同表示锁对象处于什么锁状态。、
 * 3、age。4位，表示JAVA对象的年龄，在GC中，当survivor区中对象复制一次，年龄加1，如果到15之后会移动到老年代，并发GC的年龄阈值为6.
 * 4、identity_hashcode。31位，调用方法 System.identityHashCode()计算，并会将结果写到该对象头中。当对象加锁后（偏向、轻量级、重量级），MarkWord的字节没有足够的空间保存hashCode，因此该值会移动到线程 Monitor中。
 * 5、thread。54位，持有偏向锁的线程ID。
 * 6、epoch。2位，偏向锁的时间戳。
 * 7、ptr_to_lock_record。62位，轻量级锁状态下，指向栈中锁记录的指针。
 * 8、ptr_to_heavyweight_monitor。62位，重量级锁状态下，指向对象监视器 Monitor的指针。
 *
 * Klass Word（类指针）
 * 这一部分用于存储对象的类型指针，该指针指向它的类元数据，JVM通过这个指针确定对象是哪个类的实例。该指针的位长度为JVM的一个字大小，即 32位的 JVM为 32位，64位的 JVM为 64位。
 *
 * 如果应用的对象过多，使用 64位的指针将浪费大量内存，统计而言，64位的 JVM将会比 32位的 JVM多耗费 50%的内存。为了节约内存可以使用选项 +UseCompressedOops开启指针压缩，其中，oop即 ordinary object pointer普通对象指针。开启该选项后，下列指针将压缩至32位：
 * 【1】每个 Class的属性指针（即静态变量）；
 * 【2】每个对象的属性指针（即对象变量）；
 * 【3】普通对象数组的每个元素指针；
 * 当然，也不是所有的指针都会压缩，一些特殊类型的指针 JVM不会优化，比如指向 PermGen的 Class对象指针(JDK8中指向元空间的 Class对象指针)、本地变量、堆栈元素、入参、返回值和NULL指针等。
 *
 * 数组长度
 * 如果对象是一个数组，那么对象头还需要有额外的空间用于存储数组的长度，这部分数据的长度也随着 JVM架构的不同而不同：32位的JVM上，长度为32位；64位JVM则为64位。64位 JVM如果开启 +UseCompressedOops选项，该区域长度也将由64位压缩至32位。
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/26 14:15
 * @since jdk1.8
 */
public class JolTest {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(ClassLayout.parseInstance(user).toPrintable());

        System.out.println("---------------------分割线---------------------------");

        User[] users = new User[10];
        System.out.println(ClassLayout.parseInstance(users).toPrintable());
    }
}
