package com.nca.codecamp.parser.itcommk;

import java.util.ArrayList;
import java.util.List;

import com.nca.codecamp.parser.db.DatabaseContext;


public class ITComMkParser {

  static final String BASE_URL = "http://forum.it.com.mk/";

  public static void parse() {
    final List<MainTopic> mainTopics = MainTopic.getMainTopics();
    for (MainTopic mainTopic : mainTopics) {
      final List<SubTopic> subTopics = mainTopic.getSubTopics();
      final List<String> contents = new ArrayList<>();
      for (SubTopic subTopic : subTopics) {
        contents.addAll(subTopic.getTextContents());
      }

      DatabaseContext.batchInsert(contents);
      contents.clear();

      System.out.println(mainTopic.getTitle() + " finished");

      try {
        Thread.sleep(90000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


}
