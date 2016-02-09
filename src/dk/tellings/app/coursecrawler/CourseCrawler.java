package dk.tellings.app.coursecrawler;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dk.tellings.app.backend.SchemaLocation;
import dk.tellings.app.exceptions.CourseIdInvalidException;


public class CourseCrawler {
	
	private String bodyText;
	private Document htmlDoc;
	private String courseId;
	
	public CourseCrawler(String courseId) throws IOException, CourseIdInvalidException{
		if (courseId.length() != 5) {
			throw new CourseIdInvalidException(courseId);
		}
		this.courseId = courseId;
		Connection connection = Jsoup.connect("http://www.kurser.dtu.dk/" + courseId + ".aspx");
		this.htmlDoc = connection.get();
		this.bodyText = htmlDoc.body().text();
	}
	
	public String getSchemaLocation() {
		Pattern p = Pattern.compile("([FE])\\d([BA])"); // Should find something like F2B or E5B
		Matcher m = p.matcher(bodyText); //Parse it to a matcher on the text of the html body
		
		return m.find() ? m.group() : SchemaLocation.NONE.getTrueLocation();
	}
	
	public String getCourseWebsite() {
		//Find the part of the website containing the word "Kursushjemmeside"
		Pattern p = Pattern.compile("Kursushjemmeside:");
		Matcher m = p.matcher(bodyText);
		
		String closingIn = "";
		try {
			if (m.find()) closingIn = bodyText.substring(m.start(), m.end()+100);
		} catch (Exception e) {
			//Do nothing
		}
		
		//Try to find some kind of link near the "Kursushjemmeside".
		Pattern httpFind = Pattern.compile("http.*?\\s");
		Matcher httpMatch = httpFind.matcher(closingIn);
		return (httpMatch.find()) ? httpMatch.group().trim() : "No Website Found"; 
	}
	
	public String getCourseName() {
		Pattern p = Pattern.compile(courseId+"\\s[(a-zA-Z0-9) ]*"); // Should find something like F2B or E5B
		Matcher m = p.matcher(""+htmlDoc); //Parse it to a matcher on the text of the html body
		m.find();
		return m.find() ? m.group().substring(6, m.group().length()) : SchemaLocation.NONE.getTrueLocation();
	}
	
	
}
