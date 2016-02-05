package dk.tellings.app;

import dk.tellings.app.backend.Course;
import dk.tellings.app.backend.SchemaLocation;
import dk.tellings.app.frontend.CourseBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Driver extends Application{
	
	
	public static final int CONTROLS_SIZE = 40;
	public static final int WEEKCONTAINER_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = WEEKCONTAINER_HEIGHT + CONTROLS_SIZE;
	public static final int COURSEBOX_HEIGHT = (WEEKCONTAINER_HEIGHT / 3);
	public static final int COURSEBOX_WIDTH = WINDOW_WIDTH / 5;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Course IP = new Course("Indledende Programmering", "02101", "Room 3", "Room 2", SchemaLocation.THUR_MORNING, "http://google.com");
		
		// TODO Auto-generated method stub
		primaryStage.setTitle("WeekPlan");
		
		BorderPane base = new BorderPane();
		
		CourseBox courseTest = new CourseBox(IP);
		CourseBox courseTest2 = new CourseBox(IP);
		
		base.setCenter(courseTest);
		base.setBottom(courseTest2);
		base.setPrefSize(WINDOW_WIDTH, WEEKCONTAINER_HEIGHT);
		
		primaryStage.setScene(new Scene(base));
		
		primaryStage.show();
	}
}
