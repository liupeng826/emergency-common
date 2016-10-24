package com.emergency.common.aop;

import com.emergency.common.util.MailUtil;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 邮件报警
 */
public class MailExceptionMonitor implements ExceptionCallback {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailExceptionMonitor.class);

    @Autowired
    private MailUtil mailUtil;

    private List<String> toEmails;

    private List<String> monitorExceptionList;

    @Override
    @Async
    public void callback(JoinPoint joinPoint, Throwable e) {
        String title = "[实时异常报警]调用["+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()
                +"]时出现异常";
        LOGGER.info(title);
        Object[] args = joinPoint.getArgs();
        StringBuffer argsString = new StringBuffer();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                argsString.append(args[i]);
                if(i != args.length-1) {
                    argsString.append(", ");
                }
            }
        }
        // 异常堆栈信息转化为字符串
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionContent = sw.toString();

        boolean isHasMonitorException = false;
        // 判断是否有要监控的异常
        if (monitorExceptionList != null) {
            for (String monitorException : monitorExceptionList) {
                if (exceptionContent.indexOf(monitorException) != -1) {
                    isHasMonitorException = true;
                    break;
                }
            }
        }

        String ip = "";       // ip
        String hostName = ""; // 主机名
        try{
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString();//获得本机IP　　
            hostName = addr.getHostName().toString();//获得本机名称
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        if (isHasMonitorException) {
            // build mail content
            StringBuffer content = new StringBuffer();
            content.append("<table border=1>");
            content.append("<tr><td>时间</td><td>").append(currentTime).append("</td>").append("</tr>");
            content.append("<tr><td>服务器</td><td>").append(hostName).append("(").append(ip).append(")").append("</td>").append("</tr>");
            content.append("<tr><td>参数</td><td>").append(argsString).append("</td>").append("</tr>");
            content.append("<tr><td>异常</td><td>").append(exceptionContent.replaceAll("\n", "<br/>")).append("</td></tr>");
            content.append("</table>");
            LOGGER.info("mail content:" + content);
            mailUtil.sendMail(title, content.toString(), toEmails);
        }
    }

    public List<String> getToEmails() {
        return toEmails;
    }

    public void setToEmails(List<String> toEmails) {
        this.toEmails = toEmails;
    }

    public MailUtil getMailUtil() {
        return mailUtil;
    }

    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    public List<String> getMonitorExceptionList() {
        return monitorExceptionList;
    }

    public void setMonitorExceptionList(List<String> monitorExceptionList) {
        this.monitorExceptionList = monitorExceptionList;
    }
}
