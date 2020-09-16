package pubsub;

public interface PubSub {

	void publish(Message message);

	void register(Subscriber subscriber);

}
