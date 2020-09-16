package pubsub;

public class Message {

	private final String information;

	protected Message(final String information) {
		this.information = information;
	}

	public String getInformation() {
		return information;
	}

}
