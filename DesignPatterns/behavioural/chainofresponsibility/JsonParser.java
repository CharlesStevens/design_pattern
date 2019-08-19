package chainofresponsibility;

public class JsonParser extends Parser {

	public JsonParser(Parser successor) {
		this.setSuccessor(successor);
	}

	@Override
	public void parse(String fileName) {
		if (canHandleFile(fileName, ".json")) {
			System.out.println("A JSON parser is handling the file: "
					+ fileName);
		}
		else {
			super.parse(fileName);
		}
	}
}