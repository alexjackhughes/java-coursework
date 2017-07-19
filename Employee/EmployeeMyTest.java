import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**Public tests for Ex4.
 * 
 * @author Alexander Jack Hughes
 * 
 */

public class EmployeeMyTest {

	private Employee x;
	
	@Before
	// Sets up Employee class used in tests
	public void setUp() {
		x = new Employee("Alexander Hughes", 20.0, 160); 
	}

	@Test
	// Checks all get and set methods for Employee Class
	public void getAndSetTest() {
		x.setName("Jeremy Kyle");
		x.setHourlySalary(40.2);
		x.setNumberOfHours(300);
		
		assertEquals("Jeremy Kyle", x.getName());
		assertEquals(40.2, x.getHourlySalary(),0.0000001);
		assertEquals(300, x.getNumberOfHours());
	}
	
	@Test
	public void increaseSalaryTest() { // Tests increase in salary method for Employee
		assertEquals(22.0, x.increaseSalary(10), 0.0000001);
	}
	
	@Test
	public void MonthlySalaryTest() { // Tests Monthly salary method for Employee
		assertEquals(3200.0, x.monthlySalary(), 0.0000001);
	}

}
