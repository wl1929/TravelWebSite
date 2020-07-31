package wl1929.travel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Description: 发邮件工具类
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 09:31
 */
public final class MailUtils {
    // 发件人称号，同邮箱地址
    private static final String USER = "";
    // 如果是qq邮箱可以使户端授权码，或者登录密码
    private static final String PASSWORD = "";

    /**
     * 发送验证信息的邮件
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:33
     * @param address : 收件人邮箱
     * @param text : 邮件正文
     * @param title : 标题
     * @return : boolean
     */
    public static boolean sendMail(String address, String text, String title) {
        try {
            final Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            properties.put("mail.user", USER);
            //发件人的密码
            properties.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = properties.getProperty("mail.user");
                    String password = properties.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(properties, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = properties.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(address);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
