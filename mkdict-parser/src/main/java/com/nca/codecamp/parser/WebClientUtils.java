package com.nca.codecamp.parser;

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
    webClient.getOptions().setJavaScriptEnabled(false);
    try {
      final HtmlPage page = webClient.getPage(new URL(url));
      return (List<T>) page.getByXPath(selector);
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
