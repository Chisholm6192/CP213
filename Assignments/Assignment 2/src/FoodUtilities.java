package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Ryan Chisholm - chis7577@mylaurier.ca - 169027577
 * @version 10-11-2023
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {
	int average = 0;

	for (int i = 0; i < foods.size(); i++) {
	    average += foods.get(i).getCalories(); // get calories of each food item
	}

	average /= foods.size(); // get average calories per item
	return average;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {
	int average = 0;
	int count = 0;

	for (int i = 0; i < foods.size(); i++) {
	    if (foods.get(i).getOrigin() == origin) {
		// only add to average, if origin criteria is met
		average += foods.get(i).getCalories();
		count += 1;
	    }
	}

	average /= count;

	return average;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {
	ArrayList<Food> Origin = new ArrayList();

	for (int i = 0; i < foods.size(); i++) {
	    if (foods.get(i).getOrigin() == origin) {
		// make sure origin of current food item matches desired origin
		Origin.add(foods.get(i));
	    }
	}

	return Origin;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {
	System.out.print("Name: ");
	String name = keyboard.nextLine(); // get value of name

	System.out.println(Food.originsMenu()); // display origns menu from Food class
	System.out.print("Origin: ");
	int origin = keyboard.nextInt();

	System.out.print("Vegetarian (Y/N): ");
	boolean isVegetarian = false;
	String Vegetarian = keyboard.next();
	// Set vegetarian to true or false
	if (Vegetarian == "Y") {
	    isVegetarian = true;
	} else if (Vegetarian == "N") {
	    isVegetarian = true;
	}

	System.out.print("Calories: "); // get value of calories
	int calories = keyboard.nextInt();

	Food food = new Food(name, origin, isVegetarian, calories); // create new food object

	return food;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {
	ArrayList<Food> Vegetarian = new ArrayList();

	for (int i = 0; i < foods.size(); i++) {
	    if (foods.get(i).isVegetarian()) {
		// if food is vegetarian, add to list
		Vegetarian.add(foods.get(i));
	    }
	}

	return Vegetarian;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {
	int i = 0; // int for iteration

	String name = "";
	String originString = ""; // string for origin, to read from string
	int origin = 0;
	boolean isVegetarian = false;
	String caloricString = ""; // string for calories to read from string
	int calories = 0;

	while (i < line.length() && line.charAt(i) != '|') {
	    // run until | is found, to get chars for name
	    name += line.charAt(i);
	    i += 1;
	}

	i += 1;

	while (i < line.length() && Character.isDigit(line.charAt(i))) {
	    // get digit for origin
	    originString += line.charAt(i);
	    i += 1;
	}
	origin = Integer.parseInt(originString);

	i += 1;

	// read whether vegetarian is true or false
	if (line.charAt(i) == 'f') {
	    isVegetarian = false;
	}

	else {
	    isVegetarian = true;
	}

	i += 1;

	while (i < line.length() && !(Character.isDigit(line.charAt(i)))) {
	    i += 1;
	}
	// iterate past breaks and other chars to get to calories digits

	while (i < line.length() && Character.isDigit(line.charAt(i))) {
	    // read calories digits as string
	    caloricString += line.charAt(i);
	    i += 1;
	}
	// convert to integer value
	calories = Integer.parseInt(caloricString);

	// create new food object
	Food food = new Food(name, origin, isVegetarian, calories);

	return food;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {
	ArrayList<Food> FoodList = new ArrayList();

	while (fileIn.hasNextLine()) {
	    String words = fileIn.nextLine();
	    String name = "";
	    String originString = "";
	    int origin = 0;
	    boolean isVegetarian = false;
	    String caloricString = "";
	    int calories = 0;

	    int i = 0;
	    while (i < words.length() && words.charAt(i) != '|') { // run until end of name is found
		name += words.charAt(i);
		i += 1;
	    }

	    i += 1;

	    while (i < words.length() && Character.isDigit(words.charAt(i))) {
		// run until end of digits is reached, to determine origin value
		originString += words.charAt(i);
		i += 1;
	    }
	    origin = Integer.parseInt(originString);

	    i += 1;
	    if (words.charAt(i) == 'f') { // check if vegetarian value is true or false
		isVegetarian = false;
	    } else {
		isVegetarian = true;
	    }

	    i += 1;

	    while (i < words.length() && !(Character.isDigit(words.charAt(i)))) {
		// keep progressing through String until digits are found, to find calories
		i += 1;
	    }

	    while (i < words.length() && Character.isDigit(words.charAt(i))) {
		// Process calories digits and add them to a string of values
		caloricString += words.charAt(i);
		i += 1;
	    }
	    calories = Integer.parseInt(caloricString); // convert calories into an integer value

	    // create new food object to add to arraylist
	    Food food = new Food(name, origin, isVegetarian, calories);

	    FoodList.add(food);

	}

	return FoodList;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {

	ArrayList<Food> Found = new ArrayList();

	for (int i = 0; i < foods.size(); i++) {
	    if (foods.get(i).getOrigin() == origin || origin == -1) {
		// make sure origins match, or that origins don't matter
		if (foods.get(i).getCalories() < maxCalories || maxCalories == 0) {
		    // make sure calories are within limits, or that calories don't matter
		    if (isVegetarian == true && foods.get(i).isVegetarian() == isVegetarian) {
			// make sure that if vegetarian is true, than only accept vegetarian foods
			Found.add(foods.get(i));
		    } else if (isVegetarian == false) {
			// if vegetarian is false, add all foods
			Found.add(foods.get(i));
		    }
		}
	    }
	}

	return Found;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	for (int i = 0; i < foods.size(); i++) {
	    // use the food write method to create the write the data from the list
	    foods.get(i).write(ps);
	}

    }
}
