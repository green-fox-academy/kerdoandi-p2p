package com.greenfox.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
  String currentDate;
  String logLevel;
  String endpoint;
  String methodType;
  String params;


  public Log(String endpoint, String methodType, String params) {
    currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS.SSS"));
    this.logLevel = System.getenv("CHAT_APP_LOGLEVEL");
    this.endpoint = endpoint;
    this.methodType = methodType;
    if (params != null) {
      this.params = params;
    } else {
      this.params = "";
    }
  }

  public String getCurrentDate() {
    return currentDate;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public String getMethodType() {
    return methodType;
  }

  public String getParams() {
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
      System.out.println(getCurrentDate() + " " + getLogLevel() + " Request " + getEndpoint() + " " + getMethodType() + " text=" + getParams());
    } else {
      System.out.println(getCurrentDate() + " " + getLogLevel() + " Request " + getEndpoint() + " " + getMethodType());
    }
  }
}
