package cp213;

/**
 * @author Ryan Chisholm - 169027577
 * @version 09-20-2023
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {

	double closest = v1;

	if (Math.abs(target - v1) < Math.abs(target - v2)) { // convert to absolute value to see true difference to
							     // target
	    closest = v1;
	}

	else if (Math.abs(target - v1) > Math.abs(target - v2)) {
	    closest = v2;
	}

	return closest;
    }

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {

	boolean prime = false;

	if (n > 1) { // must be greater than 1
	    if (n % 1 == 0) { // must be a whole number

		int i = 2;
		while (i < n && n % i != 0) { // check every number from 2 to n-1, to search factors
		    i += 1;

		}
		if (i == n) { // if no factors are found, it is a prime number
		    prime = true;
		}
	    }
	}

	return prime;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {

	double sum = 0.0;

	if (n > 1) { // n must be greater than 1, for it to be a series
	    int i = 1;

	    while (i <= n) { // run until n is reached, adding all the values from 1 to n
		sum += (1.0 / i);
		i += 1;
	    }
	}

	else { // if n is not greater than 1, it is not a series, so sum = n
	    sum = n;
	}

	return sum;
    }

}
