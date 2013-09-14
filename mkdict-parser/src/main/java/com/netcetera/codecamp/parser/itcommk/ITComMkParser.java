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

import java.util.List;

import com.netcetera.codecamp.parser.db.DatabaseContext;


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
