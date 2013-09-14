package com.nca.codecamp.parser.db;

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

  public void initialize() throws ClassNotFoundException {

    DatabaseUtilities.initializeJdbcDriver();

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DatabaseContext.CONNECTION_STRING);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);

      // statement.executeUpdate("drop table if exists words");
      // statement.executeUpdate("create table if not exists words (word text) ");
      statement.executeUpdate("create table if not exists words (word text) ");

    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }

  }

}
