package com.nca.codecamp.parser.itcommk;


public abstract class Topic {

  private final String topicUrl;
  private final String title;

  public Topic(final String topicUrl, final String title) {
    this.topicUrl = topicUrl;
    this.title = title;
  }

  public String getTopicUrl() {
    return topicUrl;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "Topic [title=" + title + "]";
  }

}
