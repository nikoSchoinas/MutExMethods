/*Every Thread class (BooleanThread, SwitchThread,PettersonThread,BakeryThread)
 * prints all integer numbers between 0 and 20.
 * The number of threads is equal to number of system's CPU's (except PettersonThreads) 
 * e.g. if user chooses to run BooleanThreads program will create as threads as number of system's CPUs */
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag = true;

		while (flag) {
			
			// 4 Available options.
			System.out.println("------------------------------------------");
			System.out.println("For Boolean Threads press 1.");
			System.out.println("For Switching Threads press 2.");
			System.out.println("For Petterson Threads press 3.");
			System.out.println("For Bakery Threads press 4.");
			System.out.println("To exit press 5.");
			System.out.println("------------------------------------------");

			
			System.out.println("Enter a number: ");
			
			// Reading from System.in
			
			int userChoice= new Scanner(System.in).nextInt();
			int numThreads = 0;
			numThreads = Runtime.getRuntime().availableProcessors();

			switch (userChoice) {
			case 1:
				/* Boolean Variable Threads */
				System.out.println("System's available processors: " + numThreads);
				BooleanThread[] boolThreads = new BooleanThread[numThreads];
				
				/* create threads*/
				for (int i = 0; i < numThreads; ++i) {
					boolThreads[i] = new BooleanThread();
				}
				
				/* start them up */

				System.out.println("starting threads");

				for (int i = 0; i < numThreads; ++i) {
					boolThreads[i].start();
				}

				/* wait for them to finish */

				for (int i = 0; i < numThreads; ++i) {
					try {
						boolThreads[i].join();
					} catch (InterruptedException e) {
						System.err.println("this should not happen");
					}
				}
				System.out.println("threads all done");
				break;

			case 2: 
				/* Switch Threads */
				SwitchThread.initialize(numThreads);
				SwitchThread[] switchThreads = new SwitchThread[numThreads];
				for (int i = 0; i < numThreads; ++i) {
					switchThreads[i] = new SwitchThread(i);
				}
				
				/* start them up */

				System.out.println("starting threads");

				for (int i = 0; i < numThreads; ++i) {
					switchThreads[i].start();
				}

				/* wait for them to finish */

				for (int i = 0; i < numThreads; ++i) {
					try {
						switchThreads[i].join();
					} catch (InterruptedException e) {
						System.err.println("this should not happen");
					}
				}
				System.out.println("threads all done");
				break;
			case 3:
				/* Petterson Threads */

				PettersonThread pt0 = new PettersonThread(0);
				PettersonThread pt1 = new PettersonThread(1);

				PettersonThread.initializeArray();
				
				pt0.start();
				pt1.start();

				try {
					pt0.join();
					pt1.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("threads all done");
				break;
				
			case 4: 
				/* Bakery Thread */

				BakeryThread[] bakeryThreads = new BakeryThread[numThreads];
				BakeryThread.initializeBakeryThread(numThreads);

				// Initialize the threads.
				for (int i = 0; i < numThreads; i++) {
					bakeryThreads[i] = new BakeryThread(i);
				}

				System.out.println("starting threads");

				for (int i = 0; i < numThreads; i++) {
					bakeryThreads[i].start();
				}

				for (int i = 0; i < numThreads; i++) {
					try {
						bakeryThreads[i].join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("threads all done");
				break;
			case 5:
				flag = false;
				break;
			default:
				System.out.println("Something went wrong. Please try again!");
				break;
				
			}

		}
	}

}
