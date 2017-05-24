package com.greenfox.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
  private String currentDate;
  private String logLevel;
  private String endpoint;
  private String methodType;
  private String params;

  public Log(String endpoint, String methodType, String params) {
    currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS.SSS"));
    logLevel = System.getenv("CHAT_APP_LOGLEVEL");
    this.endpoint = endpoint;
    this.methodType = methodType;
    if (params != null) {
      this.params = params;
    } else {
      this.params = "";
    }
  }

  private String getCurrentDate() {
    return currentDate;
  }

  private String getLogLevel() {
    return logLevel;
  }

  private String getEndpoint() {
    return endpoint;
  }

  private String getMethodType() {
    return methodType;
  }

  private String getParams() {
    return params;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
  }

  public void setLogLevel(String logLevel) {
    this.logLevel = logLevel;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public void setMethodType(String methodType) {
    this.methodType = methodType;
  }

  public void setParams(String params) {
    this.params = params;
  }

  public void print() {
    if (!this.params.equals("")) {
      System.out.println(getCurrentDate() + " " + getLogLevel() + " Request " + getEndpoint() +
              " " + getMethodType() + " text=" + getParams());
    } else {
      System.out.println(getCurrentDate() + " " + getLogLevel() + " Request " + getEndpoint() +
              " " + getMethodType());
    }
  }
}
