package com.nca.codecamp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.nca.codecamp.parser.db.DatabaseContext;
import com.nca.codecamp.parser.db.DatabaseContext.WordFreqEntity;

public class XmlExporter {

  public static final void main(String[] args) throws IOException {
    exportToXml();
  }

  public static void exportToXml() throws IOException {
    final FileOutputStream outputStream = FileUtils.openOutputStream(new File("wordlist.xml"));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")));

    writer.write("<wordlist>\n");
    final Collection<WordFreqEntity> wordFreqEntities = DatabaseContext.getAllWordFreqEntities();
    for (final WordFreqEntity entity : wordFreqEntities) {
      writer.write("  <w f=\"" + entity.getFreq() + "\">" + entity.getWord() + "</w>\n");
    }
    writer.write("</wordlist>");
    writer.flush();
    writer.close();
    outputStream.close();
  }

}
