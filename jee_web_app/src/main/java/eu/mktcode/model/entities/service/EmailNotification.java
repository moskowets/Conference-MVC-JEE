package eu.mktcode.model.entities.service;

import eu.mktcode.model.entities.DBEntity;

import java.time.LocalDateTime;

public class EmailNotification extends DBEntity {

    private int eventID;
    private String text;
    private boolean sent;
    private LocalDateTime nextSendAttempt;
    private String errorLog;


    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public LocalDateTime getNextSendAttempt() {
        return nextSendAttempt;
    }

    public void setNextSendAttempt(LocalDateTime nextSendAttempt) {
        this.nextSendAttempt = nextSendAttempt;
    }

    public String getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(String errorLog) {
        this.errorLog = errorLog;
    }
}
