/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.netcetera.codecamp.demo;

import java.io.IOException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class SiteLoaderDemo {

  public static final void main(String[] args) throws FailingHttpStatusCodeException, IOException {
    final WebClient webClient = new WebClient();
    
    final WebRequest request = new WebRequest(new URL("http://www.google.com"), HttpMethod.GET);
    final HtmlPage page = webClient.getPage(request);
    
    System.out.println(page.asXml());
  }
}
