package cp213;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author Ryan Chisholm
 * @version 2023-09-14
 */
public class ScannerTest {

    /**
     * Count lines in the scanned file.
     *
     * @param source Scanner to process
     * @return number of lines in scanned file
     */
    public static int countLines(final Scanner source) {
	int count = 0;

	while (source.hasNextLine()) { // if it has a next line, go to next line, increase count by 1
	    source.nextLine();
	    count += 1;
	}

	return count;
    }

    /**
     * Count tokens in the scanned object.
     *
     * @param source Scanner to process
     * @return number of tokens in scanned object
     */
    public static int countTokens(final Scanner source) {
	int tokens = 0;

	while (source.hasNext()) { // run until end of file
	    StringTokenizer tokenizer = new StringTokenizer(source.next());
	    // use tokenizer class to break down text

	    while (tokenizer.hasMoreTokens()) { // run until all tokens have been counted for
		tokens += 1;
		tokenizer.nextToken();
	    }
	}

	return tokens;
    }

    /**
     * Ask for and total integers from the keyboard.
     *
     * @param source Scanner to process
     * @return total of positive integers entered from keyboard
     */
    public static int readNumbers(final Scanner keyboard) {
	int total = 0;
	boolean end = false;

	while (!end) {

	    while (keyboard.hasNextInt()) { // run until non-integer is entered
		int input = keyboard.nextInt();
		total += input; // add to total

	    }

	    String strinput; // handle string inputs

	    if (!((strinput = keyboard.next()).equalsIgnoreCase("q"))) { // input is not q, other string
		System.out.println("'" + strinput + "'" + " is not an integer or 'q'.");
	    }

	    else { // if input was q, end loop
		end = true;
	    }
	}

	return total;
    }

}
