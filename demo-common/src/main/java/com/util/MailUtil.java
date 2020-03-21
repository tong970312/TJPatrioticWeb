package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@Component
public class MailUtil {
    // 发件人的邮箱和授权码
    @Value("${email.sendEmailAccount}")
    public  String sendEmailAccount;
    @Value("${email.sendEmailPrivateCode}")
    public  String sendEmailPrivateCode;
    // qq邮箱的 SMTP 服务器地址为: smtp.qq.com
    @Value("${email.emailSMTPHost}")
    public  String emailSMTPHost;
    //邮箱端口
    private static final String smtpPort = "465";

    public  void sendRegisterCodeEmail(String receiveAccount,String code) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", emailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session,sendEmailAccount, receiveAccount,code);
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        transport.connect(sendEmailAccount, sendEmailPrivateCode);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        transport.close();
    }

    /**创建一封邮件邮件*/

    public  MimeMessage createMimeMessage(Session session,String sendEmailAccount, String receiveAccount,String code) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendEmailAccount, "注册验证码", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(RecipientType.TO, new InternetAddress(receiveAccount, "爱国主义教育基地官方", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject("爱国主义基地账户注册验证码", "UTF-8");
        message.setText("验证码有效时间为5分钟，请勿泄露:"+code);
        // 12. 设置发件时间
        message.setSentDate(new Date());
        // 13. 保存上面的所有设置
        message.saveChanges();
        return message;
    }
}
