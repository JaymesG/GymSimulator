package assignment2;
//I pledge my honor that I have abided by the Steven's Honor System.
//Jaymes Garland

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

/**
* The ApparatusType class holds enums that
* represent different equipment in the gym.
*/
public enum ApparatusType {
	LEGPRESSMACHINE, BARBELL, HACKSQUATMACHINE, LEGEXTENSIONMACHINE, LEGCURLMACHINE,
		LATPULLDOWNMACHINE, PECDECKMACHINE, CABLECROSSOVERMACHINE;

		private static final List<ApparatusType> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));	//A copy of the values
		private static final int SIZE = VALUES.size();					//The size of values list
		private static final Random RAND = new Random();				//Used to generate random number

		/**
		* The getRandom() method is used to access a random ApparatusType.
		* @return a random ApparatusType
		*/
		public static ApparatusType getRandom() {
			return VALUES.get(RAND.nextInt(SIZE));
		}

}