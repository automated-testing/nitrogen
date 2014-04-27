package com.digitaslbi.selenium;

public enum WebsiteConnectionProblem {
    WEBLOGIC_SERVER_ISSUE("Weblogic Bridge Message", "Website not started - please check server logs."),
    SERVER_NOT_FOUND("Problem loading page", "Could not connect to host. Please check the host.env settings");

    private String pageTitleForThisIssue;
    private String messageToLog;

    WebsiteConnectionProblem(String pageTitleForThisIssue, String messageToLog) {
        this.pageTitleForThisIssue = pageTitleForThisIssue;
        this.messageToLog = messageToLog;
    }

    public String getBrowserPageTitle() {
        return pageTitleForThisIssue;
    }

    public String getFriendlyMessage() {
        return messageToLog;
    }
}
