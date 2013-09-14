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

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.netcetera.codecamp.parser.WebClientUtils;

import static com.netcetera.codecamp.parser.itcommk.ITComMkParser.BASE_URL;


class MainTopic extends Topic {
  
  private static final String TOPIC_SELECTOR = "//ol[@class='nodeList']//h3[@class='nodeTitle']//a";
  private static final String SUB_TOPIC_SELECTOR = "//ol[@class='discussionListItems']//h3[@class='title']//a";
  
  public MainTopic(String topicUrl, String title) {
    super(topicUrl, title);
  }

  public static List<MainTopic> getMainTopics() {
    final List<MainTopic> mainTopics = new ArrayList<>();
    
    final List<HtmlAnchor> topicAnchors = 
        WebClientUtils.getByXPath(BASE_URL, TOPIC_SELECTOR);
    for (HtmlAnchor anchor : topicAnchors) {
      mainTopics.add(new MainTopic(BASE_URL + anchor.getHrefAttribute(), anchor.getTextContent()));
    }
    
    return mainTopics;
  }
  
  public List<SubTopic> getSubTopics() {
    final List<SubTopic> subTopics = new ArrayList<>();
    
    final List<HtmlAnchor> topicAnchors = 
        WebClientUtils.getByXPath(getTopicUrl(), SUB_TOPIC_SELECTOR);
    for (HtmlAnchor anchor : topicAnchors) {
      subTopics.add(new SubTopic(BASE_URL + anchor.getHrefAttribute(), anchor.getTextContent()));
    }
    
    return subTopics;
  }

}
