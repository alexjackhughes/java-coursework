import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** Private Tests for Ex5.
 * 
 * @author Alexander Jack Hughes
 *
 */
public class FractionMyTest {

	private Fraction y;
	private Fraction x;

	@Before
	// Setting up two new fractions
	public void setUp() {
		y = new Fraction(5, 10);
		x = new Fraction(2, 20);
	}

	@Test
	public void getTest() { // Tests whether the get methods work
		assertEquals(2, x.getNumerator());
		assertEquals(10, y.getDenominator());
		assertEquals("2/20", x.toString());
	}
	
	@Test
	public void addTest() { // Tests whether you can add two fractions together
		
		Fraction expected = new Fraction(120, 200);
		Fraction actual = x.add(y);
		
		assertEquals(expected.getNumerator(), actual.getNumerator());
		assertEquals(expected.getDenominator(), actual.getDenominator());
		assertEquals(expected.toString(), actual.toString());
		
	}
	
	@Test
	public void multiplyTest() { // Tests whether you can multiply two fractions together
		
		Fraction expected = new Fraction(10, 200);
		Fraction actual = y.multiply(x);

		assertEquals(expected.getNumerator(), actual.getNumerator());
		assertEquals(expected.getDenominator(), actual.getDenominator());
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	public void lessThanTest() { // Tests whether one fraction is bigger than another
		assertTrue(x.less(y));
		assertFalse(y.less(x));
	}

}
