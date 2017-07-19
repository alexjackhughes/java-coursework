import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** Private Tests for Ex2.
 * 
 * @author Alexander Jack Hughes
 *
 */

public class StudentMyTest {

	private Student s; // Initializes Student Variables

	@Before
	public void setUpBeforeClass() {
		// Sets a new student object for use in tests
		s = new Student("John Smith", "5 October", "1111111", "Msc Computer Science");
	}

	@Test
	// Checks that the set and get methods work for class Employee
	public void GetVariablesTest() { 
	
		assertEquals("John Smith", s.getName()); // gets value of name of Student
		assertEquals("5 October", s.getDateOfBirth()); // gets date of birth of Student
		assertEquals("1111111", s.getStudentID()); // gets Student ID of Student
		assertEquals("Msc Computer Science", s.getDegreeProgramme()); // gets Degree of Student
	}
	
	@Test
	public void toStringTest() {
		// Tests whether method "to String" gets variables and returns then in a formatted string
		assertEquals("[John Smith, 5 October, ID: 1111111, Msc Computer Science]", s.toString());
	}

}
