package dk.tellings.app.exceptions;

import dk.tellings.app.backend.SchemaLocation;

@SuppressWarnings("serial")
public class SchemaLocationFilledException extends Exception{
	
	public SchemaLocationFilledException(SchemaLocation conflictLocation) {
		super("The location " + conflictLocation.getTrueLocation() + " is already filled.");
	}
	
}
