package com.emergency.common.util;


import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 邮件发送工具类
 */
public class MailUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

    private String hostName = "smtp.163.com";
    private String username;
    private String password;
    private String from;
    private String charset = "utf-8";
    private Boolean useSSL = true;
    private String sslSmtpPort = "465";

    public void sendMail(String title, String content, List<String> emails) {
        if(emails == null || emails.size() == 0){
            LOGGER.error("邮件[{}]收件人不能为空.", title);
            return;
        }
        HtmlEmail simpleEmail = new HtmlEmail();
        simpleEmail.setHostName(hostName);
        simpleEmail.setSSL(useSSL);
        simpleEmail.setSslSmtpPort(sslSmtpPort);
        simpleEmail.setAuthentication(username, password);
        try {
            simpleEmail.setFrom(from);
            StringBuffer tos = new StringBuffer();
            // 添加接收人
            for (String email:emails) {
                simpleEmail.addTo(email);
                tos.append(email).append(",");
            }
            simpleEmail.setCharset(charset);
            simpleEmail.setSubject(title);
            simpleEmail.setHtmlMsg(content);
            simpleEmail.send();
        } catch (Exception e) {
            LOGGER.error("邮件["+title+"]发送失败.", e);
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Boolean getUseSSL() {
        return useSSL;
    }

    public void setUseSSL(Boolean useSSL) {
        this.useSSL = useSSL;
    }

    public String getSslSmtpPort() {
        return sslSmtpPort;
    }

    public void setSslSmtpPort(String sslSmtpPort) {
        this.sslSmtpPort = sslSmtpPort;
    }
}
