//package com.iscas.common.ssh.tools;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//
//import java.io.*;
//import java.util.concurrent.TimeUnit;
//
///**
// *
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/12/9 16:19
// * @since jdk1.8
// */
//public class Test2 {
//    public static void main(String[] args) {
////        SshInfo ssh = new SshInfo("192.168.64.12", "root", "123456");
//        JSch jSch = new JSch();
//        try {
//            int port=22;
//            String host="192.168.100.96";
//            String userName="root";
//            String password="root";
//            JSch jsch = new JSch();
//            Session session = jsch.getSession(userName, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking","no");
//            session.setTimeout(6000);
//            session.connect();
//
//            Channel exec = session.openChannel("shell");
//
//            InputStream is = exec.getInputStream();
//            OutputStream os = exec.getOutputStream();
//            PrintWriter pw = new PrintWriter(os);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
////            exec.setInputStream(System.in, true);
////            exec.setOutputStream(System.out);
//            exec.connect();
//            pw.println("ls");
//            pw.flush();
//            while (true) {
//                System.out.println(br.readLine());
//                System.out.println(1111);
//            }
//        } catch (JSchException | FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
