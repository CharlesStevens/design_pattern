package com.designpatterns.behavioral.chainofresponsibilit;

import java.util.List;
import java.util.ArrayList;



public class ChainOfResponsibilityDemo {

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

      // List of file names to parse.
		List<String> fileList = populateFiles();

		// No successor for this handler because this is the last in chain.

		Parser xmlParser = new XmlParser();

		// XmlParser is the successor of CsvParser.

		Parser csvParser = new CsvParser(xmlParser);

		// CsvParser is the successor of JsonParser.

		Parser jsonParser = new JsonParser(csvParser);

		// JsonParser is the successor of TextParser.

		// TextParser is the start of the chain.

		Parser textParser = new TextParser(jsonParser);

		// Pass the file name to the first handler in the chain.

		for (String fileName : fileList) {

			textParser.parse(fileName);

		}

	}

	private static List<String> populateFiles() {

		List<String> fileList = new ArrayList<String>();
	    
	    fileList.add("csvFile.csv");
		fileList.add("someFile.txt");
		fileList.add("otherFile.json");
		fileList.add("xmlFile.xml");
	
		fileList.add("csvFile.doc");

		return fileList;

	}

}
