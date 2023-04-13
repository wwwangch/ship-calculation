package com.iscas.common.tools.core.io.file;//package com.iscas.common.tools.core.io.file;
//import jdk.internal.org.objectweb.asm.ClassReader;
//import jdk.internal.org.objectweb.asm.tree.*;
///**
// * @author zhuquanwen
// * @version 1.0
// * @date 2022/2/23 21:31
// * @since jdk1.8
// */
//public class ScannerUtilsTests {
//
//
//    public class SchedulerUtil {
//        private final static SchedulerFactory schedulerfactory = new StdSchedulerFactory();
//        private final static Map<String,Class> taskClazz = new HashMap<String,Class>();
//        static {
//            try {
//                scheduler = schedulerfactory.getScheduler();//这是单例的;
//                String path = SchedulerUtil.class.getResource("/").getPath();//获取classpath路径
//                List<File> list = getAllClassFile(new File( path ));//此方法为获取classpath路径下所有的class文件（所有包）
//                for(File f: list) {//便利每一个class文件
//                    //getClassName(f);
//                    ClassReader reader = new ClassReader(new FileInputStream(f));
//                    ClassNode cn = new ClassNode();//创建ClassNode,读取的信息会封装到这个类里面
//                    reader.accept(cn, 0);//开始读取
//                    List<AnnotationNode> annotations = cn.visibleAnnotations;//获取声明的所有注解
//                    if(annotations!=null) {//便利注解
//                        for(AnnotationNode an: annotations) {
//                            //获取注解的描述信息
//                            String anno = an.desc.replaceAll("/", ".");
//                            String annoName = anno.substring(1, anno.length()-1);
//                            if("com.mh.base.quartz.annotation.BaseQuartz".equals(annoName)) {
//                                String className = cn.name.replaceAll("/", ".");
//                                //获取注解的属性名对应的值，（values是一个集合，它将注解的属性和属性值都放在了values中，通常奇数为值偶数为属性名）
//                                String valu = an.values.get(1).toString();
//                                System.out.println(className);
//                                System.out.println(valu);
//                                taskClazz.put(valu, Class.forName(className));//根据匹配的注解，将其封装给具体的业务使用
//                            }
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
