package dk.tellings.app.backend;

public enum SchemaLocation {
	MON_MORNING(1), MON_AFTERNOON(2),
	TUE_MORNING(3), TUE_AFTERNOON(4),
	WED_MORNING(5), WED_AFTERNOON(6),
	THUR_MORNING(7), THUR_AFTERNOON(8),
	FRI_MORNING(9), FRI_AFTERNOON(10),
	NONE(0);
	
	private int location;
	
	SchemaLocation(int location) {
		this.location = location;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	
	/**
	 * @return the schema location in DTU way as a string. 
	 */
	public String getTrueLocation() {
		String trueLocation;
		
		switch (this.location) {
			case 1: trueLocation = "1A";	break;
			case 2: trueLocation = "2A";	break;
			case 3: trueLocation = "3A";	break;
			case 4: trueLocation = "4A";	break;
			case 5: trueLocation = "5A";	break;
			case 6: trueLocation = "5B";	break;
			case 7: trueLocation = "2B";	break;
			case 8: trueLocation = "1B";	break;
			case 9: trueLocation = "4B";	break;
			case 10: trueLocation = "3B";	break;
			default: trueLocation = "NONE"; break;
		}
		
		return trueLocation;
	}
}
