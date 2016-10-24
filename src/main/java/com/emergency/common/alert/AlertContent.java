package com.emergency.common.alert;


import java.util.Date;

public class AlertContent {

    public AlertContent(){
        this.alertTime = new Date();
    }

    private Date alertTime;

    private String title;

    private String message;

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AlertContent{");
        sb.append("alertTime=").append(alertTime);
        sb.append(", title='").append(title).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
