package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Ryan Chisholm - 169027577
 * @version 09-20-2023
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	boolean digits = false;

	int i = 0;
	while (i < str.length() && Character.isDigit(str.charAt(i)) == true) { // check if any non-digit characters
	    i += 1;
	}

	if (i == str.length()) { // if end of string is reached, then all characters are digits
	    digits = true;
	}

	return digits;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	boolean valid = false;

	if (sn.length() == 11) { // must have length of 11

	    if (sn.charAt(0) == 'S' && sn.charAt(1) == 'N' && sn.charAt(2) == '/') { // make sure string starts with SN/

		int i = 3;
		while (i < 6 && Character.isDigit(sn.charAt(i)) == true) { // make sure next four chars are numbers
		    i += 1;
		}

		if (i == 6) {
		    if (sn.charAt(7) == '-') { // check for hyphen in between digits
			i = 8;
			while (i < 11 && Character.isDigit(sn.charAt(i)) == true) {
			    // make sure last three chars are digits
			    i += 1;
			}

			if (i == 11) {
			    valid = true;
			}
		    }
		}
	    }
	}

	return valid;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here

	return;
    }

}
