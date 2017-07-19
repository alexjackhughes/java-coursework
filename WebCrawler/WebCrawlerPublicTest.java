import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
/**
 * Public tests for Worksheet 4, Exercise 3. Three tests with three different URLs. I shows also
 * some limitations of the program, e.g., a URL such as
 * <a style="text-decoration:none" href="blank.html">.</a> will be overlooked since it does not
 * start with something like <a href="blank.html">.
 * @author David McDonald, Manfred Kerber
 *
 */
public class WebCrawlerPublicTest {

	private String url;
	
	@Test
	public void ex3PublicTest1() {
		url = "http://cs.bham.ac.uk/~mmk/teaching/java/test.html";
		
		ArrayList<String> expected = new ArrayList<>(Arrays.asList(
				"http://www.cs.bham.ac.uk/",
				"http://www.eps.bham.ac.uk/",
				"http://www.bham.ac.uk",
				"https://canvas.bham.ac.uk/courses/21955"));
		ArrayList<String> actual = WebCrawler.collectUrls(url);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void ex3PublicTest2() {
		url = "http://www.cs.bham.ac.uk/~mmk/teaching.php";
		
		ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                                "http://www.cs.bham.ac.uk/~mmk/index.php",
                                "http://www.cs.bham.ac.uk/~mmk/index.php#address",
                                "http://www.cs.bham.ac.uk/~mmk/index.php#directions",
                                "http://www.cs.bham.ac.uk/~mmk/research.php",
                                "http://www.cs.bham.ac.uk/~mmk/research.php#researchinterests",
                                "http://www.cs.bham.ac.uk/~mmk/research.php#projects",
                                "http://www.cs.bham.ac.uk/~mmk/research.php#publications",
                                "http://www.cs.bham.ac.uk/~mmk/research.php#talks",
                                "http://www.cs.bham.ac.uk/~mmk/research.php#t",
                                "http://www.cs.bham.ac.uk/~mmk/research.php#students",
                                "http://www.cs.bham.ac.uk/~mmk/events.php",
                                "http://www.cs.bham.ac.uk/~mmk/teaching.php",
                                "https://canvas.bham.ac.uk/courses/21955",
                                "http://www.cs.bham.ac.uk/~mmk/teaching.php#student-projects.php",
                                "http://www.cs.bham.ac.uk/~mmk/teaching.php#asc-projects.php",
                                "http://www.cs.bham.ac.uk/~mmk/teaching.php#oldteaching.php",
                                "http://www.cs.bham.ac.uk/~mmk/admin.php",
                                "http://www.cs.bham.ac.uk/~mmk/admin.php#WelfareTutor",
                                "http://www.cs.bham.ac.uk/~mmk/timetable.php",
                                "http://www.cs.bham.ac.uk/~mmk/cv.php",
                                "https://canvas.bham.ac.uk/courses/21955",
                                "student-projects.php",
                                "student-projects.php",
                                "http://www.cs.bham.ac.uk/resources/courses/projects/2007/projects.html",
                                "asc-projects.php",
                                "asc-projects.php",
                                "oldteaching.php",
                                "oldteaching.php",
                                "http://www.cs.bham.ac.uk/~mmk",
                                "http://www.cs.bham.ac.uk/",
                                "http://www.bham.ac.uk/",
                                "http://validator.w3.org/check?uri=referer"));
                                                             
		ArrayList<String> actual = WebCrawler.collectUrls(url);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void ex3PublicTest3() {
		url = "http://blank.org/";
		
		ArrayList<String> expected = new ArrayList<>();
		ArrayList<String> actual = WebCrawler.collectUrls(url);
		
		assertEquals(expected, actual);
	}

}
