import java.util.concurrent.ThreadLocalRandom;

public class BooleanThread extends Thread {

	private static volatile boolean lock = false; // this variable is for lock a thread
	private static volatile int counter = 0; // This variable is just a counter.

	public BooleanThread() {
	}

	public void run() {
		while (counter < 21) {
			while (lock == true) {
				// This condition assures that a thread will be interrupted if it's going to
				// print a number bigger than 20.
				// That means it will not enter in critical section.
				if (counter > 20) {
					Thread.currentThread().interrupt();
					return;
				}
			}
			;
			lock = true; // start critical section.
			System.out.println("Thread: " + currentThread().getName() + " prints " + counter++);
			lock = false; // end critical section.
			try {
				sleep(ThreadLocalRandom.current().nextInt(1, 1000)); // random sleeping time between 1 and 1000
																		// milliseconds
			} catch (InterruptedException e) {
			}
		}

	}
}
