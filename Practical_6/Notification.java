@FunctionalInterface
interface Notifier {
	void send(String message);
}

interface Urgent {
}

@FunctionalInterface
interface UrgentNotifier extends Notifier, Urgent {
}

public class Notification {
	public static void main(String[] args) {
		Notifier emailSender = message -> System.out.println("Email: " + message);
		UrgentNotifier smsSender = message -> System.out.println("SMS: " + message);
		Notifier[] senders = { emailSender, smsSender };

		String message = "Your account has been updated.";
		for (Notifier sender : senders) {
			sender.send(message);
			if (sender instanceof Urgent) {
				sender.send(message);
			}
		}
	}
}
