import java.util.concurrent.ThreadLocalRandom;

public class PettersonThread extends Thread {
	// lock array combined with turn variable
	// defines which thread will run.
	private static volatile boolean[] lock = new boolean[2]; 
	private static volatile int turn = 0; 
	private static volatile int counter = 0; // Just a counter
	private int index;  // This variable helps while loop inside run().
	private int id; //thread identification.

	public PettersonThread(int id) {
		this.id = id;
		this.index = id;
	}
	
	public static void initializeArray() {
		//set initial values to array.
		lock[0] = true;
		lock[1] = false;
	}

	public void run() {
		while (counter < 21) {
			lock[index] = true; // if id = 0, index = 0 else index = 1.
			turn = index;
			while (lock[(index+1) % 2] && turn == (index+1) % 2) {
				//This condition  assures that a thread will be interrupted if it's going to print a number bigger than 20.
				//That means it will not enter critical section.
				if(counter>20) {
					Thread.currentThread().interrupt();
					return;
				}
			};
			//inside critical section
			System.out.println("Thread: " + currentThread().getName() + " prints " + counter++);
			lock[index] = false;
			//outside critical section.
			try {
				sleep(ThreadLocalRandom.current().nextInt(1, 1000)); // random sleeping time between 1 and 1000
																		// milliseconds
			} catch (InterruptedException e) {
			}
		}
	}
}
