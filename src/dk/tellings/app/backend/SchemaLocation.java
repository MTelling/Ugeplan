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
			case 0: trueLocation = "1A";	break;
			case 1: trueLocation = "2A";	break;
			case 2: trueLocation = "3A";	break;
			case 3: trueLocation = "4A";	break;
			case 4: trueLocation = "5A";	break;
			case 5: trueLocation = "5B";	break;
			case 6: trueLocation = "2B";	break;
			case 7: trueLocation = "1B";	break;
			case 8: trueLocation = "4B";	break;
			case 9: trueLocation = "3B";	break;
			default: trueLocation = "NONE"; break;
		}
		
		return trueLocation;
	}
	
	
	public static SchemaLocation getSchemaLocation(String str) {
		SchemaLocation sLoc;
		switch (str.substring(1,3)) {
		case "1A": sLoc = SchemaLocation.MON_MORNING;	break;
		case "2A": sLoc = SchemaLocation.MON_AFTERNOON;	break;
		case "3A": sLoc = SchemaLocation.TUE_MORNING;	break;
		case "4A": sLoc = SchemaLocation.TUE_AFTERNOON;	break;
		case "5A": sLoc = SchemaLocation.WED_MORNING;	break;
		case "5B": sLoc = SchemaLocation.WED_AFTERNOON;	break;
		case "2B": sLoc = SchemaLocation.THUR_MORNING;	break;
		case "1B": sLoc = SchemaLocation.THUR_AFTERNOON;	break;
		case "4B": sLoc = SchemaLocation.FRI_MORNING;	break;
		case "3B": sLoc = SchemaLocation.FRI_AFTERNOON;	break;
		default: sLoc = SchemaLocation.NONE; break;
		}
		
		return sLoc;
	}
}
