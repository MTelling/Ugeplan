package dk.tellings.app.backend;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Course {
	private String name;
	private String courseId;
	private String audLocation;
	private String excLocation;
	private SchemaLocation schemaLocation;
	private URI courseWebsite;
	
	
	public Course(String name, String courseId, String audLocation, String excLocation, 
			SchemaLocation schemaLocation, String courseWebsite) 
					throws URISyntaxException {
		
		this.name = name;
		this.courseId = courseId;
		this.audLocation = audLocation;
		this.excLocation = excLocation;
		this.schemaLocation = schemaLocation;
		this.courseWebsite = new URI(courseWebsite);
		
	}
	
	public void goToWebsite() throws IOException {
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().browse(this.courseWebsite);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAudLocation() {
		return this.audLocation;
	}
	
	public String getExcLocation() {
		return this.excLocation;
	}
	
	public SchemaLocation getSchemaLocation() {
		return this.schemaLocation;
	}
	
	public String getCourseId() {
		return this.courseId;
	}
	
	public String toString() {
		return "(Name: " + name + ", ID: " + courseId + ", Schema Location: " + schemaLocation.getTrueLocation() + ")";
	}
	
}
