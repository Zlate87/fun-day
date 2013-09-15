package com.nca.codecamp.parser.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ValueNormalizer {

  private static final int RANGE_MAX = 253;

  public static final void main(String[] main) {
    DatabaseUtilities.initializeJdbcDriver();
    normalize();
  }

  private static void normalize() {

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DatabaseContext.CONNECTION_STRING);
      connection.setAutoCommit(false);

      final Statement statement = connection.createStatement();
      statement.executeUpdate("drop table if exists word_freq");
      statement.executeUpdate("create table if not exists word_freq (word text, freq number, occurences number)");

      final ResultSet topOccurenceRs = statement.executeQuery("select max(occurences) from final_words");
      topOccurenceRs.next();
      final int maxOccurence = topOccurenceRs.getInt(1);
      final double ratio = calculateRatio(maxOccurence);

      final PreparedStatement insertFreqsStatement =
          connection.prepareStatement("insert into word_freq values (?, ?, ?)");

      final ResultSet finalWordsResultSet = statement.executeQuery("select word, occurences from final_words");
      while (finalWordsResultSet.next()) {
        final String word = finalWordsResultSet.getString("word");
        final int occurences = finalWordsResultSet.getInt("occurences");

        final int normalized = normalize(occurences, ratio);

        insertFreqsStatement.setString(1, word);
        insertFreqsStatement.setInt(2, normalized);
        insertFreqsStatement.setInt(3, occurences);
        insertFreqsStatement.addBatch();
      }

      insertFreqsStatement.executeBatch();

      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      DatabaseUtilities.closeConnection(connection);
    }
  }

  private static int normalize(int num, double ratio) {
    return (int) Math.ceil(num / ratio);
  }

  private static double calculateRatio(double max) {
    return max / RANGE_MAX;
  }

}
