package com.example.page;

public enum Status {
  SUCCESS("success"),
  ERROR("error"),
  STATUS400("400"),
  STATUS401("401"),
  STATUS402("402");
  
  private final String status;
  
  private Status(String status) {
    this.status = status;
  }
  
  public String toString() {
    return status;
  }
}
