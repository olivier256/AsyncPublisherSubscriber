package pubsub;

import java.util.ArrayList;
import java.util.List;

public class SyncPubSub implements PubSub {

	final List<Subscriber> subscribers;

	protected SyncPubSub() {
		subscribers = new ArrayList<>();
	}

	@Override
	public void publish(final Message message) {
		for (Subscriber subscriber : subscribers) {
			subscriber.receive(message);
		}

	}

	@Override
	public void register(final Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	public static void main(final String[] args) {
		PubSub pubSub = new SyncPubSub();
		Publisher p1 = new Publisher(pubSub);
		new Subscriber(pubSub);
		p1.publish(new Message("Hello World! 1"));
		new Subscriber(pubSub);
		p1.publish(new Message("Hello World! 2"));
	}

}
