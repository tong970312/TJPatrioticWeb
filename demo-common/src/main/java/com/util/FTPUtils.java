package com.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@Slf4j
@Component
public class FTPUtils {
    //ip 地址  用户名  密码
    @Value("${FTP.Ip}")
    private  String FTPIP;
    @Value("${FTP.User}")
    private  String FTPUser;
    @Value("${Ftp.Password}")
    private  String FTPPASSWORD;

    private String ftpIp ;
    private String ftpUser;
    private String frpPass;
    private Integer port;

    public FTPUtils() {
    }

    public FTPUtils(String ftpIp, String ftpUser, String frpPass, Integer port) {
        this.ftpIp = ftpIp;
        this.ftpUser = ftpUser;
        this.frpPass = frpPass;
        this.port = port;
    }

    /**
     * 图片上传到FTP
     */

    public boolean uploadFile(File file) throws IOException {
        FTPUtils ftpUtils = new FTPUtils(FTPIP, FTPUser, FTPPASSWORD, 21);
        return ftpUtils.uploadFiles("/usr/local/image",file,FTPIP, FTPUser, FTPPASSWORD);
    }

    /**
     * 连接FTP服务器
     */

    FTPClient ftpClient = null;

    public boolean connectFTPServer(String ftpIp, String FTPUser,String FTPPassword) {
        ftpClient = new FTPClient();
        //IP地址
        try {
            ftpClient.enterLocalPassiveMode();
            ftpClient.connect(ftpIp);
            log.info("连接FTP服务器成功：ip="+ftpIp);
            return ftpClient.login(FTPUser, FTPPassword);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("连接FTP服务器异常");
        }
        return false;
    }


    /**
     * 上传
     * @param remotePath
     * @param file
     * @return
     */
    public boolean uploadFiles(String remotePath, File file,String FTPIP, String FTPUser,String FTPPASSWORD) throws IOException {
        FileInputStream fileInputStream = null;
        //连接FTP服务器
        if (connectFTPServer(FTPIP, FTPUser, FTPPASSWORD)) {
            try {
                //切换目录到子目录
                ftpClient.changeWorkingDirectory(remotePath);
                //缓存区大小
                ftpClient.setBufferSize(1024);
                //设置字符编码
                ftpClient.setControlEncoding("UTF-8");
                //设置二进制类型
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //被动传输模式
                ftpClient.enterLocalPassiveMode();
                fileInputStream = new FileInputStream(file);
                //将对应文件读到FTP服务器上
                ftpClient.storeFile(file.getName(), fileInputStream);
                System.out.println(remotePath+" " +file.getName());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传出错");
            } finally {

                fileInputStream.close();
                ftpClient.disconnect();
            }
        }
        return false;
    }

}
