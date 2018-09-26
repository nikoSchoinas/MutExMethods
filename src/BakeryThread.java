import java.util.concurrent.ThreadLocalRandom;

public class BakeryThread extends Thread{
	
			private int id; // thread identification
			private static int counter = 0; // just a counter 
			private static int numberOfThreads;

			// Global variables for the bakery's algorithm.
			private static volatile boolean[] choosing;  // Array that helps choosing the threat that it's going to enter in critical section.
																						 
			private static volatile int[] ticket; // Defines thread priority.
			public BakeryThread(int id) {
				this.id = id;
				
				
			}
			
			public static void initializeBakeryThread(int numberOfCPUs) {
				numberOfThreads = numberOfCPUs;
				choosing = new boolean[numberOfThreads];
				ticket = new int[numberOfThreads];
				
				// Initialization.
				for (int i = 0; i < numberOfThreads; i++) {
					choosing[i] = false;
					ticket[i] = 0;
				}
			}

			 
			public void run() {

				while(counter < 21) {

					lock(id);
						// Start of critical section.
					if(counter < 21) {
						System.out.println("Thread: " + currentThread().getName() + " prints " + counter++);
						
						// sleep
						try {
							sleep(ThreadLocalRandom.current().nextInt(1, 1000));
						} catch (InterruptedException e) {  }
					}
						// End of critical section.
					unlock(id);
					
				} 

			} 

			
			public void lock(int id) {
				
				choosing[id] = true;

				// Find the max value and add 1 to get the next ticket (priority).
				ticket[id] = findMax() + 1;
				choosing[id] = false;


				for (int j = 0; j < numberOfThreads; j++) {

					
					if (j == id)
						continue;
					
					//if thread j is choosing wait.
					while (choosing[j]) { }

					//in case 2 threads make a conflict. This with the bigger ticket value or id waits.
					while (ticket[j] != 0 && (ticket[id] > ticket[j] || (ticket[id] == ticket[j] && id > j))) {}
								 
				} 
			}

			
			private void unlock(int id) {
				ticket[id] = 0;
				
			}

			
			private int findMax() {
				//this method finds the maximum number in ticket array.
				int maxNum = ticket[0];

				for (int i = 1; i < ticket.length; i++) {
					if (ticket[i] > maxNum)
						maxNum = ticket[i];
				}
				return maxNum;
			}
}
