package com.designpatterns.behavioral.chainofresponsibilit;

public class CsvParser extends Parser {

	public CsvParser(Parser successor) {
		this.setSuccessor(successor);
	}

	@Override
	public void parse(String fileName) {

		if (canHandleFile(fileName, ".csv")) {
			System.out
					.println("A CSV parser is handling the file: " + fileName);
		}

		else {
			super.parse(fileName);
		}
	}
}
