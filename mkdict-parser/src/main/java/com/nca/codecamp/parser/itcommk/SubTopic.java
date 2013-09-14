package com.nca.codecamp.parser.itcommk;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.nca.codecamp.parser.WebClientUtils;

import static com.nca.codecamp.parser.WebClientUtils.DELIMITERS;



class SubTopic extends Topic {

  private static final String CONTENT_SELECTOR =
      "//div[@class='messageContent']//blockquote[contains(@class,'messageText')]";

  public SubTopic(final String topicUrl, final String title) {
    super(topicUrl, title);
  }

  public List<String> getTextContents() {
    final List<HtmlElement> messages = WebClientUtils.getByXPath(getTopicUrl(), CONTENT_SELECTOR);
    final StringBuilder contents = new StringBuilder();
    for (HtmlElement post : messages) {
      contents.append(post.getTextContent());
    }

    return asWordArray(contents.toString().toLowerCase());
  }

  private static List<String> asWordArray(final String contents) {
    final List<String> content = new ArrayList<>();
    final StringTokenizer tokenizer = new StringTokenizer(contents, DELIMITERS);
    while (tokenizer.hasMoreElements()) {
      content.add(tokenizer.nextToken());
    }

    return content;
  }

}
