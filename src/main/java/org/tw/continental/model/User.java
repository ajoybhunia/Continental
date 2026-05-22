package org.tw.continental.model;

import org.springframework.stereotype.Component;


public class User{
  private final Integer id;
  private final String username;
  private final String password;

  public User(Integer id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername(){
    return  username;
  }
}
