package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Ryan Chisholm
 * @version 2023-11-07
 */
public class CAS extends Professor {

    String term;

    public CAS(String firstName, String lastName, String department, String term) {
	super(firstName, lastName, department);
	// call super constructor
	this.term = term;
    }

    // term getter method
    public String getTerm() {
	return this.term;
    }

    @Override
    public String toString() {
	return (super.toString() + '\n' + "Term: " + this.term);
    }

}
