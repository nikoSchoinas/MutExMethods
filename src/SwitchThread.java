import java.util.concurrent.ThreadLocalRandom;

public class SwitchThread extends Thread {

	private int  id; // thread's identification 
	private static volatile int turn = 0; // Defines which thread must run
	private static volatile int counter = 0; // Just a counter
	private static int numberOfThreads; //The number of threads is equal with the number of system's CPUs

	public SwitchThread(int id) {
		this.id = id;
	}
	
	public static void initialize( int numberOfCPUs) {
		//Every computer has different number of CPUs. 
		//This static method guarantees that the SwitchThread Class will know that number and run its code smoothly.
		numberOfThreads = numberOfCPUs;
	}

	public void run() {
		while (counter < 21) {
			while (turn != this.id) {
				//This condition  assures that a thread will be interrupted if it's going to print a number bigger than 20.
				//That means it will not enter in critical section.
				if(counter>20) {
					Thread.currentThread().interrupt();
					return;
				}
			};
			System.out.println("Thread: " + currentThread().getName() + " prints " + counter++);
			

			
			turn = (turn + 1) % numberOfThreads;
			try {
				sleep(ThreadLocalRandom.current().nextInt(1, 1000)); // random sleeping time between 1 and 1000
																		// milliseconds
			} catch (InterruptedException e) {
			}
		}
	}

}
