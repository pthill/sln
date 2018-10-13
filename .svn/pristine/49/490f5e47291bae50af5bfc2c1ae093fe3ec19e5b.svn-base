package com.sln.core.email;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MailSenderInfo {
    // 发送邮件的服务器的IP和端口  
    private String   mailServerHost;
    private String   mailServerPort = "25";
    // 邮件发送者的地址  
    private String   fromAddress;
    // 邮件接收者的地址  
    private String[]   toAddress;
    // 登陆邮件发送服务器的用户名和密码  
    private String   userName;
    private String   password;
    // 是否需要身份验证  
    private boolean  validate       = true;
    // 邮件主题  
    private String   subject;
    // 邮件的文本内容  
    private String   content;
    // 邮件附件的文件名  
    private String[] attachFileNames;
    //附件地址
    private String path;
    /** 
     * 获得邮件会话属性 
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        
        //添加属性 add by li.biao since 2018-2-27 16:34:37
        //p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.enable", "true");
        p.put("mail.debug", "true");
          
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getToAddress() {
        return toAddress;
    }

    public void setToAddress(String[] toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*
        * 退款邮件的模板
        * pc:批次 count：退货数量,money:退款总金额,status:退款状态,createTime生成时间
        * */
    public  static String productModel(String pc,Integer count,BigDecimal money,String status,Date createTime){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer buffer=new StringBuffer("");
        buffer.append("<p>Dear all:</p>");
        buffer.append("<div>下列表格为海核云谷门户电商系统模块批次退款订单信息表:</div>");
        buffer.append(" <style type=\"text/css\">\n" + "        .t1\n" + "        {\n"
                      + "            clear: both;\n" + "            border: 1px solid #2c2c2c;\n"
                      + "        }\n" + "        .t1 tr th\n" + "        {\n"
                      + "            color: #2c2c2c;\n" + "            line-height: 30px;\n"
                      + "            font-weight: normal;\n" + "            padding-left: 5px;\n"
                      + "            padding-right: 5px;\n" + "        }\n" + "        .t1 tr td\n"
                      + "        {\n" + "           \n" + "            padding-bottom: 5px;\n"
                      + "            padding-top: 5px;\n" + "            color: #2c2c2c;\n"
                      + "            border-top: 1px solid #2c2c2c;\n"
                      + "            padding-left: 5px;\n" + "            padding-right: 5px;\n"
                      + "            word-break: break-all;\n" + "        }\n" + "    </style>");
        buffer.append("<table width=\"80%\" border=\"0\" class=\"t1\" align=\"center\" cellpadding=\"0\"\n"
                      + "  cellspacing=\"0\"><tr align=\"center\"><th bgcolor=\"#e0e0e0\">退款批次</th><th bgcolor=\"#e0e0e0\">生成时间</th>"
                      + "<th bgcolor=\"#e0e0e0\">退款金额</th><th bgcolor=\"#e0e0e0\">退货数量</th>"
                      + "<th bgcolor=\"#e0e0e0\">批次状态</th></tr><tr align=\"center\"><td>"
                      +pc+ "</td><td>"+"</td><td>"+money+"</td><td>"+count+"</td><td>"+status
                      + "</td></tr></table>");
        buffer.append("<div>附件为系统退款批次明细表格，请审阅。有任何疑问题可随时向海核云谷运营中心反馈</div>");
        System.out.println(buffer.toString());
        return buffer.toString();
    }
}
