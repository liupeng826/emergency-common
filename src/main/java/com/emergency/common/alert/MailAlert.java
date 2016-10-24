package com.emergency.common.alert;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MailAlert implements Alert {

    private static Logger logger = LoggerFactory.getLogger(MailAlert.class);

    private List<String> emails = new ArrayList<String>();

    private String hostName = "smtp.163.com";
    private String username;
    private String password;
    private String charset = "utf-8";
    private Boolean useSSL = true;
    private String sslSmtpPort = "465";
    private String from;
    private String template = "%s \r\n \r\n 报警时间：%s";

    @Override
    public void alert(AlertContent alertContent) {
        HtmlEmail simpleEmail = new HtmlEmail();
        simpleEmail.setHostName(hostName);
        simpleEmail.setSSL(useSSL);
        simpleEmail.setSslSmtpPort(sslSmtpPort);
        simpleEmail.setAuthentication(username, password);
        try {
            simpleEmail.setFrom(from);
            StringBuffer tos = new StringBuffer();
            if(emails != null && emails.size() > 0){
                // 添加接收人
                for (String email:emails) {
                    simpleEmail.addTo(email);
                    tos.append(email).append(",");
                }
                simpleEmail.setCharset(charset);
                simpleEmail.setSubject(alertContent.getTitle());
                String content = String.format(template, alertContent.getMessage(), DateFormatUtils.ISO_DATETIME_FORMAT.format(alertContent.getAlertTime()));
                simpleEmail.setHtmlMsg(content);
                simpleEmail.send();
                logger.info("send {} to {}.", content, tos.substring(0, tos.length()-1));
            } else {
                logger.warn("send {} fail, emails is null", alertContent);
            }
        } catch (EmailException e){
            logger.error("fail to send mail: " + e.getMessage(), e);
        } catch (Exception e){
            logger.error("fail to send mail: " + e.getMessage(), e);
        }
    }


    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
