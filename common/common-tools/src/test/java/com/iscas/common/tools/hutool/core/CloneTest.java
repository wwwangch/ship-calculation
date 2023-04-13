package com.iscas.common.tools.hutool.core;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.util.ObjectUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * 克隆单元测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 15:26
 * @since jdk1.8
 */
public class CloneTest {
    /**
     * 测试Cloneable接口的方式
     * */
    @Test
    public void test1(){
        Man man = new Man();
        man.setAge(13);
        man.setName("张三");
        man.setWeight("65");
        Phone phone = new Phone();
        phone.setNum("13587457847");
        man.setPhone(phone);
        Man man2 = man.clone();
        Assertions.assertFalse(man == man2);
        System.out.println(man);
        System.out.println(man2);
        //从下面的输出可以看到嵌套对象使用的浅复制，如果想深度复制，需要使用序列化的方式
        Assertions.assertEquals(man.getPhone().hashCode(), man2.getPhone().hashCode());
        System.out.println(man.getPhone().hashCode());
        System.out.println(man2.getPhone().hashCode());

    }
    /**测试CloneSupport方式*/
    @Test
    public void test2(){
        Women women = new Women();
        women.setAge(14);
        women.setName("张颖");
        Women women2 = women.clone();
        Assertions.assertFalse(women == women2);
        System.out.println(women.hashCode());
        System.out.println(women2.hashCode());
    }

    /**测试深度复制*/
    @Test
    public void test3(){

        Man man = new Man();
        man.setAge(13);
        man.setName("张三");
        man.setWeight("65");
        Phone phone = new Phone();
        phone.setNum("13587457847");
        man.setPhone(phone);

        Man man2 = ObjectUtil.cloneByStream(man);
        Assertions.assertFalse(man == man2);
        System.out.println(man);
        System.out.println(man2);
        //从下面的输出可以看到嵌套对象使用的深度复制，所有嵌套对象都是新对象
        Assertions.assertNotEquals(man.getPhone().hashCode(), man2.getPhone().hashCode());
        System.out.println(man.getPhone().hashCode());
        System.out.println(man2.getPhone().hashCode());
    }


    private static class Man extends Animal implements Cloneable<Man>, Serializable {
        private String name;
        private int age;
        private Phone phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Phone getPhone() {
            return phone;
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        @Override
        public Man clone() {
            try {
                return (Man)super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return "Man{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", phone=" + phone +
                    ", weight='" + getWeight() + '\'' +
                    '}';
        }
    }

    private static class Animal implements Serializable{
        private String weight;

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "weight='" + weight + '\'' +
                    '}';
        }
    }

    private static class Phone implements Serializable{
        private String num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "phone{" +
                    "num='" + num + '\'' +
                    '}';
        }
    }

    private static class Women extends CloneSupport<Women> implements  Serializable{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Women{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
