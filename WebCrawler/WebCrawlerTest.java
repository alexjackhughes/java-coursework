import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * 
 * Private tests for Worksheet 4, Exercise 3.
 * 
 * 3 Tests to check that static method getURLs works as expected,
 * and returns an ArrayList of the correct links.
 * 
 * @author Alexander Jack Hughes
 *
 */

public class WebCrawlerTest {

	private String url;
	
	@Test
	public void privateTest1() {
		url = "http://alexjackhughes.com/contact/"; // Contact page including multiple "a href" links
		
		ArrayList<String> expected = new ArrayList<>(Arrays.asList(
				"http://alexjackhughes.com/", 
				"http://alexjackhughes.com/about/", 
				"http://alexjackhughes.com/webdesign/", 
				"http://alexjackhughes.com/blog/", 
				"http://alexjackhughes.com/contact/", 
				"mailto:alexjackhughes@gmail.com",
				"mailto:alexjackhughes@gmail.com\" target=\"_blank", 
				"mailto:alexjackhughes@gmail.com\" target=\"_blank"));
		ArrayList<String> actual = WebCrawler.collectUrls(url);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void privateTest2() {
		url = "http://www.brainjar.com/java/host/test.html"; // A blank HTML page
		
		ArrayList<String> expected = new ArrayList<>();
		ArrayList<String> actual = WebCrawler.collectUrls(url);
		
		assertEquals(expected, actual); // Should equal a blank array
	}
	
	@Test
	public void privateTest3() { // Returns links of web page
		url = "http://wildwildwest.warnerbros.com/cmp/frameset.html";
		
		ArrayList<String> expected = new ArrayList<>(Arrays.asList(
				"http://www.netscape.com\" target=\"offsite", 
				"http://www.microsoft.com\" target=\"offsite"));
		
		ArrayList<String> actual = WebCrawler.collectUrls(url);
		
		assertEquals(expected, actual); // Should equal above links
	}
}
