package dk.tellings.app.backend;

public enum SchemaLocation {
	MON_MORNING(0), MON_AFTERNOON(1),
	TUE_MORNING(2), TUE_AFTERNOON(3),
	WED_MORNING(4), WED_AFTERNOON(5),
	THUR_MORNING(6), THUR_AFTERNOON(7),
	FRI_MORNING(8), FRI_AFTERNOON(9),
	NONE(10);
	
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
	
	public int getArrayLocation(String str) {
		int index;
		
		switch (str) {
			case "1A": index = 0;	break;
			case "2A": index = 1;	break;
			case "3A": index = 2;	break;
			case "4A": index = 3;	break;
			case "5A": index = 4;	break;
			case "5B": index = 5;	break;
			case "2B": index = 6;	break;
			case "1B": index = 7;	break;
			case "4B": index = 8;	break;
			case "3B": index = 9;	break;
			default: index = 10; break;
		}
		
		return index;
	}
}
