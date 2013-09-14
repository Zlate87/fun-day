package com.netcetera.codecamp;

import com.netcetera.codecamp.parser.db.Initializer;
import com.netcetera.codecamp.parser.itcommk.ITComMkParser;


/**
 * Hello world!
 */
public class Main {

  public static void main(String[] args) throws ClassNotFoundException {
    new Initializer().initialize();
    
    ITComMkParser.parse();
    
    System.out.println("Finished");
  }
  
}
