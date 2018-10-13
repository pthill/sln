package com.sln.core.email;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailSender {

    private static Logger log = LogManager.getLogger(MailSender.class);

    /** 
     * 以HTML格式发送邮件 
     * @param mailInfo 待发送的邮件信息 
     */
    public boolean sendHtmlMail(MailSenderInfo mailInfo) {
        try {
            // 判断是否需要身份认证  
            EmailAuthenticator authenticator = null;
            Properties pro = mailInfo.getProperties();
            // 如果需要身份认证，则创建一个密码验证器  
            if (mailInfo.isValidate()) {
                authenticator = new EmailAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassword());
            }
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session  
            Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

            // 根据session创建一个邮件消息  
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址  
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者  
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            InternetAddress[] sendTo = new InternetAddress[mailInfo.getToAddress().length];
            for (int i = 0; i < mailInfo.getToAddress().length; i++)
            {
                System.out.println("发送到:" + mailInfo.getToAddress()[i]);
                log.info("接收者邮件地址:"+mailInfo.getToAddress()[i]);
                sendTo[i] = new InternetAddress(mailInfo.getToAddress()[i]);
            }
            //Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO, sendTo);
            //mailMessage.setRecipient(Message.RecipientType.TO, sendTo);
            // 设置邮件消息的主题  
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            System.out.println(new Date());
            log.info("邮件发送时间"+new Date());
            mailMessage.setSentDate(new Date());

            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart  
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容  
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            //设置信件的附件(用本地上的文件作为附件)  
            html = new MimeBodyPart();
            log.info("附件路径"+mailInfo.getPath());
            FileDataSource fds = new FileDataSource(mailInfo.getPath());
            DataHandler dh = new DataHandler(fds);
            html.setFileName(javax.mail.internet.MimeUtility.encodeWord("退款批次明细.xls"));
            html.setDataHandler(dh);
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容  
            mailMessage.setContent(mainPart);
            mailMessage.saveChanges();
            // 发送邮件  
            Transport.send(mailMessage);
            log.info("邮件发送成功");
            System.out.println("发送成功");
            return true;
        } catch (Exception ex) {
            log.error("发送邮件异常，目标邮件地址为" + mailInfo.getToAddress() + "，标题为" + mailInfo.getSubject()
                      + "，异常信息：" + ex.getMessage());
            throw new RuntimeException(ex.getClass().getName() + " : " + ex.getMessage());
        }
    }

    /** 
     * 以文本格式发送邮件 
     *  
     * @param mailInfo 
     *            待发送的邮件的信息 
     */
    public boolean sendTextMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证  
        EmailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器  
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session  
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息  
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址  
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者  
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中  
            //Address to = new InternetAddress(mailInfo.getToAddress());
            InternetAddress[] sendTo = new InternetAddress[mailInfo.getToAddress().length];
            for (int i = 0; i < mailInfo.getToAddress().length; i++)
            {
                System.out.println("发送到:" + mailInfo.getToAddress()[i]);
                sendTo[i] = new InternetAddress(mailInfo.getToAddress()[i]);
            }
            mailMessage.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO, sendTo);
            //mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题  
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间  
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容  
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件  
            Transport.send(mailMessage);
            System.out.println("邮件发送完成");
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
