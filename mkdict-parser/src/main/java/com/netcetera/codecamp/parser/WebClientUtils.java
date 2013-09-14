/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.netcetera.codecamp.parser;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public final class WebClientUtils {
  
  public static final String DELIMITERS = "\".;:' ()/\\-+=[]`„“*$%^&@!?,<>{}\t\n\r";
  
  @SuppressWarnings("unchecked")
  public static <T extends HtmlElement> List<T> getByXPath(final String url, final String selector) {
    final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
    try {
      final HtmlPage page = webClient.getPage(new URL(url));
      return (List<T>) page.getByXPath(selector);
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
