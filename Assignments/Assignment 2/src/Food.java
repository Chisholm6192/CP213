package cp213;

import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author Ryan Chisholm - chis7577@mylaurier.ca - 169027577
 * @version 10-11-2023
 */
public class Food implements Comparable<Food> {

    // Constants
    public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek",
	    "Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

    /**
     * Creates a string of food origins in the format:
     *
     * <pre>
    Origins
    0 Canadian
    1 Chinese
    ...
    11 English
     * </pre>
     *
     * @return A formatted numbered string of valid food origins.
     */
    public static String originsMenu() {
	// construct a string of a list of origins and their numbers
	StringBuilder origin = new StringBuilder();

	for (int i = 0; i < ORIGINS.length; i++) {
	    if (i < ORIGINS.length - 1) {
		origin.append(String.format("%2d %s%n", i, ORIGINS[i]));
		// newline separating items
	    }

	    else {
		origin.append(String.format("%2d %s", i, ORIGINS[i])); // no newline on last element
	    }
	}

	return origin.toString();
    }

    // Attributes
    private String name = null;
    private int origin = 0;
    private boolean isVegetarian = false;
    private int calories = 0;

    /**
     * Food constructor.
     *
     * @param name         food name
     * @param origin       food origin code
     * @param isVegetarian whether food is vegetarian
     * @param calories     caloric content of food
     */
    public Food(final String name, final int origin, final boolean isVegetarian, final int calories) {
	this.name = name;
	this.origin = origin;
	this.isVegetarian = isVegetarian;
	this.calories = calories;

	return;
    }

    /*
     * (non-Javadoc) Compares this food against another food.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /**
     * Foods are compared by name, then by origin if the names match. Must ignore
     * case.
     */
    @Override
    public int compareTo(final Food target) {
	int result = 0;

	if (this.name.toLowerCase().equals(target.name.toLowerCase())) {
	    // names are the same
	    if (this.origin != target.origin) {
		// origin numbers aren't equal

		if (this.origin < target.origin) { // current origin < target, than current comes before target
		    result = -1;
		}

		else { // current origin > target, than current comes after target
		    result = 1;
		}
	    }
	}

	else { // names aren't the same
	    if (this.name.charAt(0) < target.name.charAt(0)) { // current name comes first
		result = -1;
	    }

	    else { // target name comes first
		result = 1;
	    }
	}

	return result;
    }

    /**
     * Getter for calories attribute.
     *
     * @return calories
     */
    public int getCalories() {

	return calories;
    }

    /**
     * Getter for name attribute.
     *
     * @return name
     */
    public String getName() {

	return name;
    }

    /**
     * Getter for origin attribute.
     *
     * @return origin
     */
    public int getOrigin() {

	return origin;
    }

    /**
     * Getter for string version of origin attribute.
     *
     * @return string version of origin
     */
    public String getOriginString() {
	return ORIGINS[this.origin];
	// return string value of origin
    }

    /**
     * Getter for isVegetarian attribute.
     *
     * @return isVegetarian
     */
    public boolean isVegetarian() {

	return isVegetarian;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object//toString() Creates a formatted string of food data.
     */
    /**
     * Returns a string version of a Food object in the form:
     *
     * <pre>
    Name:       name
    Origin:     origin string
    Vegetarian: true/false
    Calories:   calories
     * </pre>
     */
    @Override
    public String toString() {
	String foodObj = "";

	foodObj = "Name: " + "      " + this.name + '\n' + "Origin: " + "    " + getOriginString() + '\n'
		+ "Vegetarian: " + this.isVegetarian + '\n' + "Calories: " + "  " + this.calories + '\n';
	// create an output string for food objects

	return foodObj;
    }

    /**
     * Writes a single line of food data to an open PrintStream. The contents of
     * food are written as a string in the format name|origin|isVegetarian|calories
     * to ps.
     *
     * @param ps The PrintStream to write to.
     */
    public void write(final PrintStream ps) {
	String food = "";
	food = name + "|" + origin + "|" + isVegetarian + "|" + calories + '\n';
	// build formatted string
	ps.print(food);

	return;
    }

}
