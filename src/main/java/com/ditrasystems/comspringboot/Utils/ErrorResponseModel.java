package com.ditrasystems.comspringboot.Utils;


public class ErrorResponseModel {
  private int status;
  private int errorCode;
  private String message;

  public ErrorResponseModel(int status, int errorCode, String message) {
    this.status = status;
    this.errorCode = errorCode;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}