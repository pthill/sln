package com.sln.core.email;

import com.sln.core.SlnConfig;

public class SendMail {

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public boolean send163Email(String[] to, String subject, String body,String path) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setToAddress(to); // 设置接受者邮箱地址  
        mailInfo.setSubject(subject);//标题
        mailInfo.setContent(body);//内容

        //这个类主要是设置邮件  
        mailInfo.setMailServerHost(SlnConfig.MAIL_SERVER_HOST);
        mailInfo.setMailServerPort(SlnConfig.MAIL_SERVER_PORT);
        mailInfo.setValidate(true);

        String sender = SlnConfig.SEND_EMAIL_NAME;
        mailInfo.setUserName(sender); // 实际发送者
        mailInfo.setPassword(SlnConfig.SEND_EMAIL_PASSWORD);// 您的邮箱密码 
        mailInfo.setFromAddress(sender); // 设置发送人邮箱地址  
        if (path!=null&&!path.equals("")){//设置附件地址
            mailInfo.setPath(path);
        }
        // 这个类主要来发送邮件  
        MailSender sms = new MailSender();
        //sms.sendTextMail(mailInfo); // 发送文体格式
        return sms.sendHtmlMail(mailInfo); // 发送html格式  
    }
}
