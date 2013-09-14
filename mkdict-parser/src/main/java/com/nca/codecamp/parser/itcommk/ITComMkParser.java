package com.nca.codecamp.parser.itcommk;

import java.util.List;

import com.nca.codecamp.parser.db.DatabaseContext;


public class ITComMkParser {

  static final String BASE_URL = "http://forum.it.com.mk/";

  public static void parse() {
    final List<MainTopic> mainTopics = MainTopic.getMainTopics();
    for (MainTopic mainTopic : mainTopics) {
      final List<SubTopic> subTopics = mainTopic.getSubTopics();
      for (SubTopic subTopic : subTopics) {
        final List<String> contents = subTopic.getTextContents();
        DatabaseContext.batchInsert(contents);
        contents.clear();
      }
    }
  }


}
