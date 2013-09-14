package com.nca.codecamp.parser.db;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseUtilities {

  public static void closeConnection(final Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    }
  }

  public static void initializeJdbcDriver() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      e.printStackTrace(System.err);
      throw new RuntimeException(e);
    }
  }

}
