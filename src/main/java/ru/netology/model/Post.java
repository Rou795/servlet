package ru.netology.model;

public class Post {
  private long id;
  private String content;
  private boolean actual;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
    this.actual = true;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setStatus(boolean status) {
    this.actual = status;
  }

  public boolean isActual() {
    return actual;
  }
}
