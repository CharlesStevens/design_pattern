package chainofresponsibility;

public class XmlParser extends Parser {

	@Override
	public void parse(String fileName) {

		if (canHandleFile(fileName, ".xml")) {
			System.out
					.println("A XML parser is handling the file: " + fileName);
		}
		else {
			super.parse(fileName);
		}
	}
}

