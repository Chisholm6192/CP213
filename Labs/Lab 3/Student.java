package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Ryan Chisholm
 * @version 10-05-2023
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {
	this.surname = surname;
	this.forename = forename;
	this.birthDate = birthDate;
	this.id = id;
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";

	string = "Name: " + "     " + this.surname + ", " + this.forename + "\n" + "ID: " + "       " + this.id + "\n"
		+ "Birthdate: " + this.birthDate;

	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = 0;

	if (this.surname.equals(target.surname)) { // last names are the same
	    if (this.forename.equals(target.forename)) { // forenames are the same
		if (this.id < target.id) { // this id is lower number
		    result = -1;
		} else if (this.id > target.id) { // this id is higher number
		    result = 1;
		}

	    } else { // forenames are not the same
		if (this.forename.compareTo(target.forename) < 0) {
		    result = -1;
		} else {
		    result = 1;
		}
	    }
	} else {
	    if (this.surname.compareTo(target.surname) < 0) {
		result = -1;
	    } else {
		result = 1;
	    }
	}
	return result;
    }

    // Getters
    public LocalDate getBirthDate() {
	return birthDate;
    }

    public String getSurname() {
	return surname;
    }

    public String getForename() {
	return forename;
    }

    public int getId() {
	return id;
    }

    // Setters
    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public void setForename(String forename) {
	this.forename = forename;
    }

    public void setId(int id) {
	this.id = id;
    }
}
