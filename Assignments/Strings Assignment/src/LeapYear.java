package cp213;

/**
 * @author Ryan Chisholm - 169027577
 * @version 09-18-2023
 */
public class LeapYear {

    /**
     * Determines whether or not a year is a leap year.
     *
     * A leap year is defined as:
     *
     * "Every year that is exactly divisible by four is a leap year, except for
     * years that are exactly divisible by 100, but these centurial years are leap
     * years if they are exactly divisible by 400. For example, the years 1700,
     * 1800, and 1900 are not leap years, but the years 1600 and 2000 are." (United
     * States Naval Observatory)
     *
     * Thus 1996, 2000, and 2004 are leap years, but 1899, 1900, and 1901 are not
     * leap years."
     *
     * @param year The year to test (int greater than 0)
     * @return true if year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(final int year) {

	boolean LeapYear = false; // leap year is false by default

	if (year % 4 == 0) {
	    if (year % 100 != 0) { // if evenly divisible by 4 but not by 100, the year is a leap year
		LeapYear = true;

	    } else {
		if (year % 400 == 0) { // leap year is divisible by 100, but not by 400, the year is a leap year
		    LeapYear = true;
		}
	    }
	}

	return LeapYear;
    }

}
