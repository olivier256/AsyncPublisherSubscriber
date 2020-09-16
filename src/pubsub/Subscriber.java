package pubsub;

public class Subscriber {

	public Subscriber(final PubSub pubSub) {
		pubSub.register(this);
	}

	public void receive(final Message message) {
		System.out.println("Je suis " + this + " et j'ai reçu le message '" + message.getInformation() + "'");
	}

}
