package com.nca.codecamp.parser.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;


public class DatabaseContext {

  static final String CONNECTION_STRING = "jdbc:sqlite::resource:words.db";

  static {
    DatabaseUtilities.initializeJdbcDriver();
  }

  public static void batchInsert(final Collection<String> words) {
    batchInsert(words.toArray(new String[words.size()]));
  }

  public static void batchInsert(final String[] words) {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(CONNECTION_STRING);
      connection.setAutoCommit(false);

      final PreparedStatement statement =
          connection.prepareStatement("insert into words values (?)");
      for (String word : words) {
        statement.setString(1, word);
        statement.addBatch();
      }

      statement.executeBatch();
      statement.close();

      connection.commit();

    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }
  }

}
