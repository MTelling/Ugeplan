package dk.tellings.app.backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import dk.tellings.app.exceptions.SchemaLocationFilledException;

public class WeekPlan {
	private List<Course> courseList;
	
	public WeekPlan() {
		this.courseList = new LinkedList<Course>();
	}
	
	public void addCourse(Course course) throws SchemaLocationFilledException {
		//Check if the location in the schema is already filled. 
		if (course.getSchemaLocation() != SchemaLocation.NONE && schemaLocationFilled(course.getSchemaLocation())) {
			throw new SchemaLocationFilledException(course.getSchemaLocation());
		}
		
		//Add course if no exceptions are thrown. 
		this.courseList.add(course); 
	}
	
	/**
	 * Removes a course which fits the search term.
	 * @param searchTerm
	 * @return true - if the course was removed. 
	 */
	public boolean removeCourse(String searchTerm) {
		boolean courseIsRemoved = false;
		
		//Go through the list of courses and check if there is any with the search term.
		Optional<Course> courseToRemove = courseList.stream().
				filter(course -> course.getCourseId().contains(searchTerm) || 
						course.getName().contains(searchTerm))
				.findAny();
		
		//If the course exists, remove it.
		if (courseToRemove.isPresent()) {
			courseList.remove(courseToRemove.get());
			courseIsRemoved = true;
		}
		
		return courseIsRemoved;
	}
	
	/**
	 * Checks if a given schema location is already filled. 
	 * @param schemaLocation
	 * @return true if schema location is already filled. 
	 */
	private boolean schemaLocationFilled(SchemaLocation schemaLocation) {
		boolean locationFilled = false;
		
		if (courseList.stream()
				.filter(course -> course.getSchemaLocation().equals(schemaLocation))
				.count() > 0) {
			locationFilled = true;
		}
		
		return locationFilled;
	}
	
	public String toString() {
		return this.courseList.toString();
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}
	
	public Course getCourse(int index) {
		return courseList.get(index);
	}

}
