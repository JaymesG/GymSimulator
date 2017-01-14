package assignment2;
//I pledge my honor that I have abided by the Steven's Honor System.
//Jaymes Garland
import java.util.Set;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
* The Gym class is used to generate clients with random
* routines and simulate the use of gym equipment with
* semaphores.
*/
public class Gym implements Runnable {

	private static final int GYM_SIZE = 30;
	private static final int GYM_REGISTERED_CLIENTS = 100;
	//private static final int GYM_REGISTERED_CLIENTS = 1000;
	
	private Set<Client> clients;						//List of clients
	private ExecutorService executor;					//For threads
	private Map<ApparatusType, Semaphore> atPerms;		//Apparatus Type Permissions
	private Map<WeightPlateSize, Semaphore> wpPerms;	//WeightPlateSize Permissions
	
	/**
	* The Gym() constructor creates an instance of the runnable
	* Gym object, initiates all fields, and creates a list of
	* client objects.
	*/
	public Gym() {

		//Initialize fields
		clients = new HashSet<Client>();
		executor = Executors.newFixedThreadPool(GYM_SIZE);
		atPerms = new HashMap<ApparatusType, Semaphore>();
		wpPerms = new HashMap<WeightPlateSize, Semaphore>();

		//Set permissions for each WeightPlateSize
		wpPerms.put(WeightPlateSize.LARGE_10KG, new Semaphore(20));
		wpPerms.put(WeightPlateSize.MEDIUM_5KG, new Semaphore(30));
		wpPerms.put(WeightPlateSize.SMALL_3KG, new Semaphore(40));

		//Set permissions for each ApparatusType
		atPerms.put(ApparatusType.LEGPRESSMACHINE, new Semaphore(5));
		atPerms.put(ApparatusType.BARBELL, new Semaphore(5));
		atPerms.put(ApparatusType.HACKSQUATMACHINE, new Semaphore(5));
		atPerms.put(ApparatusType.LEGEXTENSIONMACHINE, new Semaphore(5));
		atPerms.put(ApparatusType.LEGCURLMACHINE, new Semaphore(5));
		atPerms.put(ApparatusType.LATPULLDOWNMACHINE, new Semaphore(5));
		atPerms.put(ApparatusType.PECDECKMACHINE, new Semaphore(5));
		atPerms.put(ApparatusType.CABLECROSSOVERMACHINE, new Semaphore(5));

		//Create a list of randomly generated clients
		for(int id = 1; id <= GYM_REGISTERED_CLIENTS; id++) {
			clients.add(Client.generateRandom(id));
		}
	}
	
	/**
	* The run() method is used to simulate the interleavings inside
	* a gym simulation. When this method is called as a runnable to
	* the thread in Assignment2 class, it iterates through the
	* client list and creates unique threads for each client. The
	* clients then use the gym equipment with the use of semaphores.
	*/
	public void run() {
		//To ensure that only one client can access a weight plate at a time.
		//Linux-lab wants this semaphore to be final
		final Semaphore getWP = new Semaphore(1);
		for(Client cl : clients) {
			//Linux-lab wants client to be final
			final Client client = cl;
			executor.execute(new Runnable() {
				public void run() {
					for(Exercise exercise : client.getRoutine()) {
						try {
							//To ensure that only one client can use the available
							//apparatus' and weightplates at a time.
							getWP.acquire();
							atPerms.get(exercise.getApparatus()).acquire();

							wpPerms.get(WeightPlateSize.SMALL_3KG).acquire
							(exercise.getWeight().get(WeightPlateSize.SMALL_3KG));
							wpPerms.get(WeightPlateSize.MEDIUM_5KG).acquire
							(exercise.getWeight().get(WeightPlateSize.MEDIUM_5KG));
							wpPerms.get(WeightPlateSize.LARGE_10KG).acquire
							(exercise.getWeight().get(WeightPlateSize.LARGE_10KG));
							//Print results of aquiring AP and WP
							client.printAcquire(exercise);
							//Allow another client to access weightplates.
							getWP.release();
							//Print results of workout and activate duration
							client.printWorkout(exercise);
							Thread.sleep(exercise.getDuration());

						}catch(InterruptedException e) {
							e.printStackTrace();
						}

						//Release any weightplates so other clients can access them
						wpPerms.get(WeightPlateSize.SMALL_3KG).release
						(exercise.getWeight().get(WeightPlateSize.SMALL_3KG));
						wpPerms.get(WeightPlateSize.MEDIUM_5KG).release
						(exercise.getWeight().get(WeightPlateSize.MEDIUM_5KG));
						wpPerms.get(WeightPlateSize.LARGE_10KG).release
						(exercise.getWeight().get(WeightPlateSize.LARGE_10KG));
						//Release the apparatus so other clients can access them
						atPerms.get(exercise.getApparatus()).release();
						//Print results of release
						client.printRelease(exercise);
					}
				}
			});
		}
		//Kill the thread when completed
		executor.shutdown();
	}
}