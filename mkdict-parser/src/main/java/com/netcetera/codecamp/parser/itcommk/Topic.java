/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.netcetera.codecamp.parser.itcommk;


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
