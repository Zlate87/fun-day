package com.nca.codecamp.parser;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;


public class ContentFilter {

  public static void filter(final Collection<String> collection) {
    for (Iterator<String> iterator = collection.iterator(); iterator.hasNext(); ) {
      if (!isAcceptable(iterator.next())) {
        iterator.remove();
      }
    }
  }

  public static boolean isAcceptable(final String item) {
    return StringUtils.isAlpha(item);
  }

}
