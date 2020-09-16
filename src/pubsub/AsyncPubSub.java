package pubsub;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class AsyncPubSub implements PubSub, Runnable {
	private static final Logger log = Logger.getLogger("AsyncPubSub");

	private final Queue<Message> queue;

	private final List<Subscriber> subscribers;

	private boolean running;

	protected AsyncPubSub() {
		queue = new ConcurrentLinkedQueue<>();
		subscribers = new ArrayList<>();
		running = true;
		new Thread(this).start();
	}

	@Override
	public void publish(final Message message) {
		queue.add(message);
	}

	@Override
	public void register(final Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void run() {
		while (running) {
			Message message = queue.poll();
			if (message != null) {
				System.out.println("Je suis " + this + " et je dois diffuser le message '" + message.getInformation() + "'");
				for (Subscriber subscriber : subscribers) {
					subscriber.receive(message);
				}
			}
		}
	}

	public void close() {
		running = false;
	}

	public static void main(final String[] args) {
		AsyncPubSub pubSub = new AsyncPubSub();
		Publisher p = new Publisher(pubSub);
		new Subscriber(pubSub);
		p.publish(new Message("Hello World! 1"));
		sleep(10);
		new Subscriber(pubSub);
		p.publish(new Message("Hello World! 2"));
		pubSub.close();
	}

	private static void sleep(final long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			log.warning(e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
}
