package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Ryan Chisholm
 * @version 2023-11-07
 */
public class IA extends Student {

    private String course;

    public IA(String lastName, String firstName, String id, String course) {
	super(lastName, firstName, id);
	// call super constructor
	this.course = course;
    }

    // course getter method
    public String getCourse() {
	return this.course;
    }

    @Override
    public String toString() {
	return (super.toString() + '\n' + "Course: " + this.course);
    }
}
