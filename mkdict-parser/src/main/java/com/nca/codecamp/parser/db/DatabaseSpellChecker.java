package com.nca.codecamp.parser.db;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class DatabaseSpellChecker {

  public static void main(String[] args) throws IOException {
    DatabaseUtilities.initializeJdbcDriver();
    doSpellCheck();
  }

  private static void doSpellCheck() throws IOException {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DatabaseContext.CONNECTION_STRING);
      connection.setAutoCommit(false);

      final List<String> lines = FileUtils.readLines(new File("mk_MK.dic"), Charset.forName("UTF-8"));

      final Statement statement = connection.createStatement();
      statement.executeUpdate("drop table if exists spellcheck ");
      statement.executeUpdate("create table spellcheck (word text)");

      final PreparedStatement preparedStatement = connection.prepareStatement("insert into spellcheck values (?)");
      for (String line : lines) {
        preparedStatement.setString(1, line);
        preparedStatement.addBatch();
      }

      preparedStatement.executeBatch();

      statement.executeUpdate("drop table if exists final_words");
      statement.executeUpdate("create table final_words (word text, occurences number)");

      statement.executeUpdate("insert into final_words "
          + "select wo.word, wo.occurence as occurences from word_occurence wo "
          + "where wo.word in (select * from spellcheck)");


      statement.executeUpdate("vacuum;"); // reduce extra table space

      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }
  }

}
