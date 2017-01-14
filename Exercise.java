package assignment2;
//I pledge my honor that I have abided by the Steven's Honor System.
//Jaymes Garland

import java.util.Map;
import java.util.Random;

/**
* The Exercise class is used by a client in the simulation.
* The exercise includes weights and an apparatus.
*/
public class Exercise {
	
	private int duration;							//Milliseconds exercise takes
	private ApparatusType at;						//Apparatus of the exercise
	private static Random rand = new Random();		//For generating random numbers
	private Map<WeightPlateSize, Integer> weight;	//Weights used in exercise
	
	/**
	* The Exercise constructor is used to create and instance of the exercise object.
	* @param the ApparatusType that is used in this exercise
	* @param the set of WeightPlateSizes used in the exercise
	* @param an int that represents how long this exercise takes in miliseconds
	*/
	public Exercise(ApparatusType at, Map<WeightPlateSize, Integer> weight, int duration) {
		this.at = at;
		this.weight = weight;
		this.duration = duration;
	}
	
	/**
	* The generateRandom() method is used to create a random exercise.
	* @param a map holding the number of each weightplatesize in this random exercise
	* @return a random exercise with random attributes
	*/
	public static Exercise generateRandom(Map<WeightPlateSize, Integer> weight) {
		//The amount of time in milliseconds is between closed interval [1, 10] 
		int dur = rand.nextInt(10) + 1;
		return new Exercise(ApparatusType.getRandom(), weight, dur);
	}

	/**
	* The getDuration() method is used to access the duration of this exercise.
	* @return an int indicating how long this exercise sould take
	*/
	public int getDuration() {
		return duration;
	}

	/**
	* The getWeight() method is used to access the weight in this exercise.
	* @return the map holding the number of each type of WeightPlateSize 
	*/
	public Map<WeightPlateSize, Integer> getWeight() {
		return weight;
	}

	/**
	* The getApparatus() method is used to access the ApparatusType
	* in this exercise.
	* @return the ApparatusType used in this exercise
	*/
	public ApparatusType getApparatus() {
		return at;
	}
}