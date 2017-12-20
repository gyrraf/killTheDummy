/**
 * A textbased, turnbased game where you try to kill the enemy before the enemy kills you.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017
 */
package killTheDummy;

/**
 * A few useful methods used at different points in the program.
 * @author	Raffael Gyr
 * @version	1.0
 */
public class Useful extends Object {
	
	//there shouldn't be any objects of this class.
	@SuppressWarnings("unused")
	private Useful useful = new Useful();
	/**
	 * constructor. It's private because there shouldn't be any objects of this class.
	 */
	private Useful() {
	}
	
	/**
	 * returns the percentage 'percentage' of the 'number' after it is rounded to 0 decimal places.
	 * @param	number		the number of which the rounded percentage value gets returned
	 * @param	percentage	the number of percents that are wanted
	 * @return	the rounded percentage value
	 */
	public static int percentageRounded(int number, int percentage) {
		//int to float
		float value = intToFloat(number);
		float percent = intToFloat(percentage);
		
		//calculate it
		float exactResult = value / 100;
		exactResult = exactResult * percent;
		
		//round and convert to int
		int solution = floatToInt(round(exactResult));
		
		return solution;
	} //End of method 'percentageRounded(int, int): int'
	
	/**
	 * returns the number 'number' divided by the 'divisor' after it is rounded to 0 decimal places.
	 * @param	number	the number of which the divided and then rounded value gets returned
	 * @param	divisor	the number whith which the 'number' gets divided
	 * @return	the rounded, divided number
	 */
	public static int divisionRounded(int number, int divisor) {
		//int to float
		float numberF = intToFloat(number);
		float divisorF = intToFloat(divisor);
		
		//calculate
		float result = numberF / divisorF;
		
		//round and convert to int
		int solution = floatToInt(round(result));
		
		return solution;
	} //End of method 'divisionRounded(int, int): int'
	
	/**
	 * rounds the number 'number' to 0 decimal places
	 * @param	number	the unrounded number
	 * @return	the to 0 decimal places rounded number
	 */
	public static float round(float number) {
		//find out the part before the decimal point and substract it
		float ground = 0.0f;
		for (float f = 0.0f; f <= number; f = f + 1.0f) {
			ground = f;
		}
		ground = ground - 1.0f; //correct value because it is 1 too high
		float decimalPlaces = number - ground;
		
		//find out if the decimal places are equal or larger than 0.5 or if they are smaller
		float result = 0.0f;
		if (decimalPlaces >= 0.5f) {
			result = ground + 1.0f;
		} else {
			result = ground;
		}
		
		return result;
	} //End of method 'round(float): int'
	
	/**
	 * converts a float to an int. Warning: the number gets rounded.
	 * @param	number	the number as a float
	 * @return	the number as an int
	 */
	public static int floatToInt(float number) {
		Float obj = new Float(number);
		int result = obj.intValue();
		
		return result;
	} //End of method 'floatToInt(float): int'
	
	/**
	 * converts an int to a float.
	 * @param	number	the number as an int
	 * @return	the number as a float
	 */
	public static float intToFloat(int number) {
		Integer obj = new Integer(number);
		float result = obj.floatValue();
		
		return result;
	} //End of method 'intToFloat(int): float'
	
	/**
	 * Tests if a number is dividable through the divisor or not
	 * 
	 * @author		Raffael Gyr
	 * @version		1.0
	 * @since		31.07.2017
	 * @throws		ArithmeticException	division by zero Exception
	 *
	 * @return 				dividable or not dividable
	 * @param number		the number to be tested
	 * @param divisor		the divisor to be tested on
	 */
	public static boolean isDividable(int number, int divisor) throws ArithmeticException {
		try {
			if((number % divisor) == 0) {
				return (true);
			} else {
				return (false);
			} //End of if
		} catch (ArithmeticException arithmeticEx) {
			throw arithmeticEx = new ArithmeticException("division by zero");
		}
	} //End of method 'isDividable(int, int): boolean'
}