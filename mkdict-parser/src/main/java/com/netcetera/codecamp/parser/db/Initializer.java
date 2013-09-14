/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.netcetera.codecamp.parser.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Initializer {

  public static void main(String[] args) throws ClassNotFoundException {
    new Initializer().initialize();
    // DatabaseContext.batchInsert(new String[] { "hello", "world" });
    System.out.println("database initialization finished");
  }

  private void initialize() throws ClassNotFoundException {

    Class.forName("org.sqlite.JDBC");
    
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DatabaseContext.CONNECTION_STRING);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10); 

      statement.executeUpdate("drop table if exists words");
      // statement.executeUpdate("create table if not exists words (word text) ");
      statement.executeUpdate("create table words (word text) ");
      
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }

  }

}
