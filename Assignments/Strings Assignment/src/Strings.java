package cp213;

/**
 * @author Ryan Chisholm - 169027577
 * @version 09-20-2023
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	boolean palindrome = false;

	String words = string.toUpperCase(); // make enire word same case

	words = words.replaceAll("[^a-zA-Z]+", ""); // get rid of any non-alphabetic chars

	if (words.charAt(0) == words.charAt(words.length() - 1)) { // check to make sure first and last chars match
	    int i = 0;
	    int l = words.length() - 1;
	    // i and l run from opposite ends of the string to check if the characters are
	    // the same on each side

	    while ((i < words.length() / 2 && l > words.length() / 2) && (words.charAt(i) == words.charAt(l))) {
		// runs until a non matching value is found, or until i and l meet in the middle
		i += 1;
		l -= 1;
	    }

	    if (i == l) {
		// if there are no non-matching characters, than the string is a palindrome
		palindrome = true;
	    }
	}

	return palindrome;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	boolean valid = false;

	if ((name.charAt(0) == '_' && name.length() > 1) || (Character.isLetter(name.charAt(0)) == true)) {
	    // name must start with a letter or underscore, but cannot be a sole underscore
	    int i = 0;

	    while (i < name.length() && (Character.isLetter(name.charAt(i)) == true
		    || Character.isDigit(name.charAt(i)) == true || name.charAt(i) == '_')) {
		// make sure each char in name is a letter, number or underscore
		i += 1;
	    }

	    if (i == name.length()) { // if end of loop was reached, that means the name is valid
		valid = true;
	    }
	}

	return valid;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	String PigLatin = word.toLowerCase();

	int i = 0;
	boolean found = false;

	while (i < word.length() && found == false) { // run until end of string is reached, or a vowel is found

	    int j = 0;

	    if (i == 0) { // if checking first letter, y cannot be a vowel
		while (j < VOWELS.length() && word.charAt(i) != VOWELS.charAt(j)) {

		    j += 1;
		}

	    }

	    else { // if checking letters that aren't first, y can be a vowel
		while (j < VOWELS.length() && word.charAt(i) != VOWELS.charAt(j) && word.charAt(i) != 'y') {
		    j += 1;
		}

	    }

	    if (j < VOWELS.length()) { // if vowel is found
		found = true;
	    }

	    else {
		i += 1;
	    }
	}

	if (i == 0) { // if first letter is vowel
	    PigLatin = PigLatin + "way";
	}

	else { // first letter is consonant
	    PigLatin = PigLatin.substring(i, PigLatin.length()) + PigLatin.substring(0, i) + "ay";
	}

	if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') { // fix casing
	    PigLatin = PigLatin.substring(0, 1).toUpperCase() + PigLatin.substring(1, PigLatin.length());
	}

	return PigLatin;
    }

}
