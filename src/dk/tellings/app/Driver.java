package dk.tellings.app;

import java.net.URISyntaxException;

import dk.tellings.app.exceptions.SchemaLocationFilledException;

public class Driver {
	public static void main(String[] args) {
		WeekPlan plan = new WeekPlan();
		
		Course first = null, second = null, third = null;
		try {
			first = new Course("IP", "02101", "Room 3", "Room 4", SchemaLocation.FRI_AFTERNOON, "http://google.dk");
			second = new Course("second", "02001", "Room 3", "Room 4", SchemaLocation.FRI_MORNING, "http://google.dk");
			third = new Course("third", "01111", "Room 3", "Room 4", SchemaLocation.FRI_AFTERNOON, "http://google.dk");
		} catch (URISyntaxException e1) {
			System.out.println("URL INCLOMPLETE");
		}
		
		
		try {
			plan.addCourse(first);
			plan.addCourse(second);
			
			System.out.println(plan);
		} catch (SchemaLocationFilledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			plan.addCourse(third);
			
			System.out.println(plan);
		} catch (SchemaLocationFilledException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: " + e.getMessage());
		}
		
		plan.removeCourse("IP");
		plan.removeCourse("0201");
		
		System.out.println(plan);
		
	}
}
