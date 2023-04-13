package com.iscas.common.ssh.tools.model;

import com.jcraft.jsch.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Ssh-Client创建结果
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/9 17:07
 * @since jdk1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SshClientDto {

    private Session session;

    private PrintWriter pw;

    private BufferedReader br;

    private InputStream is;

    private OutputStream os;

}
