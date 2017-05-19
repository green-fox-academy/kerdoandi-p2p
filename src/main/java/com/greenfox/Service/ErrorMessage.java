package com.greenfox.Service;

public class ErrorMessage {
  String status;
  String error;

  public ErrorMessage(String error) {
    status = "error";
    this.error = error;
  }

  public String getError() {
    return error;
  }
}