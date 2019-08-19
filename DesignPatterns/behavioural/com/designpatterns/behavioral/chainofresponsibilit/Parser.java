package com.designpatterns.behavioral.chainofresponsibilit;

public class Parser {

	private Parser successor;

	public void parse(String fileName) {

		if (getSuccessor() != null) {

			getSuccessor().parse(fileName);
		}

		else {

			System.out.println("Unable to find the correct parser for the file: " + fileName);

		}

	}

	protected boolean canHandleFile(String fileName, String format) {

		return (fileName == null) || (fileName.endsWith(format));

	}

	Parser getSuccessor() {

		return successor;

	}

	void setSuccessor(Parser successor) {

		this.successor = successor;

	}

}
