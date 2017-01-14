package assignment2;
//I pledge my honor that I have abided by the Steven's Honor System.
//Jaymes Garland

import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

/**
* The Client class creates and instance of the client object
* which is used in the gym simulation. Each client has and id
* and a routine (list) of exercises that they will perform
* in the gym.
*/
public class Client {
	private int id;								//Id of client
	private List<Exercise> routine;				//List of exercise client performs
	private static Random rand = new Random();	//For creating random numbers

	/**
	* The Client() constructor creates and instance of
	* the client class and initializes its id and routine.
	* @param an int that represents the clients id
	*/
	public Client(int id) {
		this.id = id;
		routine = new ArrayList<Exercise>();
	}
	
	/**
	* The addExercise() method is used to add an exercise
	* to the routine (list) of exercises that the client
	* will perform.
	* @param and Exercise object to add to clients routine
	*/
	public void addExercise(Exercise e) {
		routine.add(e);
	}
	
	/**
	* The generateRandom() method is used to generate
	* a random client for the simulation.
	* @param a int that represents this random clients id
	* @return the randomly generated client
	*/
	public static Client generateRandom(int id) {
		Client randomClient = new Client(id);
		int sWeights, mWeights, lWeights;
		//Max number of exercises in closed interval [15, 20]
		int totalExercises = rand.nextInt(6) + 15;
		//Generate all exercises for this random client
		for(int i = 0; i < totalExercises; i++) {
			//Generate random exercise with random number
			//of weight plates for each size
			Map<WeightPlateSize, Integer> totalWeights =
				new HashMap<WeightPlateSize, Integer>();
			//Number of weightplatesize's is no less than 1 and no greater than 10
			do {
				sWeights = rand.nextInt(10);
				mWeights = rand.nextInt(10);
				lWeights = rand.nextInt(10);
				if((sWeights + mWeights + lWeights) == 0)
					mWeights = 1;
			}while(sWeights + mWeights + lWeights > 10);

			//Add weightplatesizes and their corrosponding amount to the map
			totalWeights.put(WeightPlateSize.values()[0], sWeights);
			totalWeights.put(WeightPlateSize.values()[1], mWeights);
			totalWeights.put(WeightPlateSize.values()[2], lWeights);
			//Add random exercise to random client
			randomClient.addExercise(Exercise.generateRandom(totalWeights));
		}

		return randomClient;
	}

	/**
	* The getID() method is used to access the id field in this class.
	* @return a int that represents this clients id
	*/
	public int getID() {
		return id;
	}

	/**
	* The getRoutine() method is used to access the routine field in this class.
	* @return a list of exercises that make up the routine of this client
	*/
	public List<Exercise> getRoutine(){
		return routine;
	}

	/**
	* The printAquire() method prints the results of a client
	* aquiring both an Apparatus and the WeightPlateSize's that
	* they use for that Apparatus.
	* @param exercise object that holds the Apparatus and WeightPlateSize of the current acquire
	*/
	public void printAcquire(Exercise ex) {
		System.out.println("------------------ CLIENT " + id + 
						   " -------------------\n ACQUIRED " + ex.getApparatus() +
						   "\n [(SMALL_3KG, " + ex.getWeight().get(WeightPlateSize.SMALL_3KG) + 
						   "), (MEDIUM_5KG, " + ex.getWeight().get(WeightPlateSize.MEDIUM_5KG) + 
						   "), (LARGE_10KG, " + ex.getWeight().get(WeightPlateSize.LARGE_10KG) +
						   ")]\n---------------------------------------------------\n");
	}

	/**
	* The printWorkout() method prints the results of a client's workout and the duration it
	* took to complete this workout.
	* @param exercise object that holds the duration the client will use the equipment
	*/
	public void printWorkout(Exercise ex) {
		System.out.println("------------------ CLIENT " + id + " -------------------\n" + 
						   " NOW USING THE " + ex.getApparatus() + " FOR (" + ex.getDuration() +
						   "ms)\n---------------------------------------------------\n");
	}

	/**
	* The printRelease() method prints the results of a client
	* releasing both an Apparatus and the WeightPlateSize's that
	* they use for that Apparatus.
	* @param exercise object that holds the Apparatus and WeightPlateSize of the current release
	*/
	public void printRelease(Exercise ex) {
		System.out.println("------------------ CLIENT " + id + " -------------------\n RELEASED " + 
						   ex.getApparatus() +
						   "\n [(SMALL_3KG, " + ex.getWeight().get(WeightPlateSize.SMALL_3KG) + 
						   "), (MEDIUM_5KG, " + ex.getWeight().get(WeightPlateSize.MEDIUM_5KG) + 
						   "), (LARGE_10KG, " + ex.getWeight().get(WeightPlateSize.LARGE_10KG) +
						   ")]\n---------------------------------------------------\n");
	}
}