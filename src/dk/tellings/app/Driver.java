package dk.tellings.app;

import dk.tellings.app.backend.Course;
import dk.tellings.app.backend.SchemaLocation;
import dk.tellings.app.backend.WeekPlan;
import dk.tellings.app.frontend.SettingsPane;
import dk.tellings.app.frontend.WeekContainer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Driver extends Application{
	
	
	public static final int CONTROLS_SIZE = 40;
	public static final int WEEKCONTAINER_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = WEEKCONTAINER_HEIGHT + CONTROLS_SIZE;
	public static final int COURSEBOX_HEIGHT = (WEEKCONTAINER_HEIGHT / 3);
	public static final int COURSEBOX_WIDTH = WINDOW_WIDTH / 5;
	public static WeekPlan weekPlan;
	public static StackPane root;
	private static WeekContainer weekContainer;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Course IP = new Course("Indledende Programmering", "02101", "Room 3", "Room 2", SchemaLocation.MON_AFTERNOON, "http://google.com");
		Course d = new Course("Indledende Programmering2", "021101", "Room 3", "Room 2", SchemaLocation.TUE_MORNING, "http://google.com");
		
		// TODO Auto-generated method stub
		primaryStage.setTitle("WeekPlan");
		
		root = new StackPane();
		
		weekPlan = new WeekPlan();
		weekPlan.addCourse(new Course());
		weekPlan.addCourse(IP);
		weekPlan.addCourse(d);
		while (weekPlan.getCourseList().size() < 10) {
			weekPlan.addCourse(new Course());
		}
		
		
		weekContainer = new WeekContainer(weekPlan);
		root.getChildren().add(weekContainer);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.sizeToScene();
		
		//Add stylesheet "layout.css"
		scene.getStylesheets().add(this.getClass().getResource("layout.css").toExternalForm());
		
		/**
		 * Listen for keypressses
		 * 
		 * When ESC is pressed:
		 * If the upper most child of root is not the WeekContainer, remove the uppermost child from the root.
		 * If the upper most child of root is the WeekContainer, present the settings pane. 
		 */
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				KeyCode keyCode = event.getCode();
				if (keyCode == KeyCode.ESCAPE) {
					Node upperMostChild = root.getChildren().get(root.getChildren().size() - 1);
					
					if (upperMostChild instanceof WeekContainer) {
						root.getChildren().add(new SettingsPane());
					} else {
						root.getChildren().remove(upperMostChild);
						root.getChildren().get(0).setEffect(null);
					}
				}
			}
			
		});
		
		primaryStage.show();
		
	}
	
	public static void printWeekPlan() {
		System.out.println(weekPlan);
	}
	
	public static void updateWeekContainer(Course course, int i) {
		weekContainer.updateCourseBox(i, course);
	}
	
	public static void overWriteWeekContainer(Course course, int i) {
		
	}
	
}
