//package com.iscas.common.ssh.tools;
//
//import com.jcraft.jsch.ChannelExec;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import org.apache.commons.io.IOUtils;
//
//import java.io.*;
//public class Test1 {
//    public static void main(String[] args) throws JSchException, IOException {
//        //建立连接
//        int port=22;
//        String host="192.168.100.96";
//        String userName="root";
//        String password="root";
//        JSch jsch = new JSch();
//        Session session = jsch.getSession(userName, host, port);
//        session.setPassword(password);
//        session.setConfig("StrictHostKeyChecking","no");
//        session.setTimeout(6000);
//        session.connect();
//        //建立连接结束
//        //发送指令
//        ChannelExec exec = (ChannelExec) session.openChannel("exec");
//        InputStream in = exec.getInputStream();
//
//        exec.setCommand("date");
//        exec.connect();
//        String s = IOUtils.toString(in, "UTF-8");
//        System.out.println("结果："+s);
//        in.close();
//
//
//        ChannelExec exec2 = (ChannelExec) session.openChannel("exec");
//        InputStream in2 = exec2.getInputStream();
//
//        exec2.setCommand("ls");
//        exec2.connect();
//        String s2 = IOUtils.toString(in2, "UTF-8");
//        System.out.println("结果："+s2);
//        in2.close();
//
//
//        session.disconnect();
//    }
//}