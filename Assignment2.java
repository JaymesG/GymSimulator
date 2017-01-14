package assignment2;
//I pledge my honor that I have abided by the Steven's Honor System.
//Jaymes Garland

/**
* The Assignment2 class is used to create a thread
* that will hold an instance of the Gym() class
* to simulate the interleavings of a gym.
*/
public class Assignment2 {
	public static void main(String[] args) {
		Thread thread = new Thread(new Gym());
		thread.start();
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}