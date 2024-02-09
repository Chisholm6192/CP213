package cp213;

/**
 * @author Ryan Chisholm - 169027577
 * @version 09-18-2023
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {
	String cipher = s.toUpperCase(); // string that enciphered chars are added to
	String word = s.toUpperCase(); // second string used to allow program not to repeat from using the cipher
				       // variable

	if (n != 0) { // don't run if there is no shift

	    for (int i = 0; i < word.length(); i++) {

		if (Character.isLetter(word.charAt(i)) == true) {

		    int l = 0;
		    while (l < ALPHA_LENGTH && word.charAt(i) != ALPHA.charAt(l)) {
			// search for letter in alphabet, to find position

			l += 1;
		    }

		    if (l + n < ALPHA_LENGTH) { // if the shift does not go out of bounds
			cipher = cipher.substring(0, i) + ALPHA.charAt(l + n)
				+ cipher.substring(i, cipher.length() - 1);
		    }

		    else if (l + n >= ALPHA_LENGTH) { // if the shift goes out of bounds, loop around to first letter of
						      // alphabet
			int num = (l + n) - 26;
			cipher = cipher.substring(0, i) + ALPHA.charAt(num) + cipher.substring(i, cipher.length() - 1);
		    }

		}

	    }
	}

	return cipher;
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

	String word = s.toUpperCase();
	String sub = s.toUpperCase();

	for (int i = 0; i < word.length(); i++) { // for loop to iterate through string

	    if (Character.isLetter(word.charAt(i)) == true) {

		int l = 0;
		while (l < ALPHA_LENGTH && word.charAt(i) != ALPHA.charAt(l)) { // find location of current char in
										// alphabet
		    l += 1;
		}

		sub = sub.substring(0, i) + ciphertext.charAt(l) + sub.substring(i, sub.length() - 1);
		// replace current char with char at same location in ciphertext
	    }
	}

	return sub;
    }

}
