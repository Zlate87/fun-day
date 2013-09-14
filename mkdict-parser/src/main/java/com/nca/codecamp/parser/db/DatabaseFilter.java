package com.nca.codecamp.parser.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.nca.codecamp.parser.ContentFilter;


public class DatabaseFilter {

  public static final void main(String args[]) {
    DatabaseUtilities.initializeJdbcDriver();
    filterContents();
    System.out.println("done");
  }


  private static void filterContents() {
    final Collection<String> words = new ArrayList<>();

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DatabaseContext.CONNECTION_STRING);

      final Statement statement = connection.createStatement();
      final ResultSet resultSet = statement.executeQuery("select word from words");
      while (resultSet.next()) {
        words.add(resultSet.getString("word"));
      }

      System.out.println("starting filtering, array size: " + words.size());
      ContentFilter.filter(words);
      System.out.println("filtering finished, array size: " + words.size());

      statement.executeUpdate("create table words_backup (word text)");
      statement.executeUpdate("insert into words_backup select word from words");
      statement.executeUpdate("delete from words");

      System.out.println("batch insert...");
      DatabaseContext.batchInsert(words);
      System.out.println("batch insert end");

    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }
  }

}
