import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**Public tests for Ex3.
 * 
 * @author Alexander Jack Hughes
 * 
 */

public class WeightMyTest {

	private Weight w3;

	@Before
	public void setUp() {
		w3 = new Weight(7.23); // Sets up the new weight at 7.23KG
	}

	@Test
	 // Checks whether weight (given in KG) is converted into correct amount of pounds
	public void getPoundsTest() {
		
		double expected = 15.939421556;
		assertEquals(expected, w3.getPounds(), 0.00000001);
	
	}
	
	@Test
	 // Checks whether weight (given in KG) is converted into correct amount of Kilograms
	public void getKilogramsTest() {
		
		double expected2 = 7.23;
		assertEquals(expected2, w3.getKilograms(), 0.00000001);

	}
	
	@Test
	 // Checks whether weight (given in KG) is converted into correct amount of Ounces
	public void getOuncesTest() {
		
		double expected3 = 255.030744895;
		assertEquals(expected3, w3.getOunces(), 0.00000001);
	}

}
