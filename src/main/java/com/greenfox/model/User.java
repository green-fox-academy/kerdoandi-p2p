package com.greenfox.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb.user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;

  public User(String name) {
    this.name = name;
  }

  public User() {
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
