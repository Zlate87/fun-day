package com.nca.codecamp;

import com.nca.codecamp.parser.db.Initializer;
import com.nca.codecamp.parser.itcommk.ITComMkParser;

public class Main {

  public static void main(String[] args) throws ClassNotFoundException {
    new Initializer().initialize();

    ITComMkParser.parse();

    System.out.println("Finished");
  }

}
