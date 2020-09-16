package pubsub;

public class Publisher {

	private final PubSub pubSub;

	protected Publisher(final PubSub pubsub) {
		pubSub = pubsub;
	}

	public void publish(final Message message) {
		System.out.println("Je suis " + this + " et je publie le message '" + message.getInformation() + "'");
		pubSub.publish(message);
	}

}
