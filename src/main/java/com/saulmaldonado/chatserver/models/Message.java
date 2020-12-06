package com.saulmaldonado.chatserver.models;

public class Message implements IMessage {
  private String message;

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String setMessage(String message) {
    this.message = message;
    return message;
  }

  @Override
  public boolean clearMessage() {
    try {
      message = null;
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
