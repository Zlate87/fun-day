package com.nca.codecamp.parser.itcommk;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.nca.codecamp.parser.WebClientUtils;

import static com.nca.codecamp.parser.itcommk.ITComMkParser.BASE_URL;



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
