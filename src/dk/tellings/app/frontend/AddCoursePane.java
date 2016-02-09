package dk.tellings.app.frontend;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import dk.tellings.app.Driver;
import dk.tellings.app.backend.Course;
import dk.tellings.app.backend.SchemaLocation;
import dk.tellings.app.coursecrawler.CourseCrawler;
import dk.tellings.app.exceptions.CourseIdInvalidException;
import dk.tellings.app.exceptions.SchemaLocationFilledException;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class AddCoursePane extends Presenter {

	private TextField idField;
	private TextField schemaField;
	private TextField websiteField;
	public static final double WIDTH = Driver.WINDOW_WIDTH - 100;
	private Alert alert = new Alert(AlertType.NONE);
	private TextField nameField;
	private HBox nameContainer;
	private HBox schemaContainer;
	private HBox websiteContainer;
	private TextField audRoomField;
	private HBox audRoomContainer;
	private TextField excRoomField;
	private HBox excRoomContainer;
	private Button addCourseButton;

	public AddCoursePane() {
		super(7, WIDTH);

		idField = new TextField("Enter course id");
		HBox searchContainer = hContainer(idField, "ID:");
		
		nameField = new TextField("Enter course name");
		nameContainer = hContainer(nameField, "Course Name:");
		
		//TODO: add some check for this
		schemaField = new TextField("Enter schema location");
		schemaContainer = hContainer(schemaField, "Schema Location:");

		//TODO: add some check for this
		websiteField = new TextField("Enter course website");
		websiteContainer = hContainer(websiteField, "Website:");
		
		//TODO: Add sanitizing
		audRoomField = new TextField("Enter course aud. room");
		audRoomContainer = hContainer(audRoomField, "Aud. room:");
		
		//TODO: Add sanitizing
		excRoomField = new TextField("Enter course exc. room");
		excRoomContainer = hContainer(excRoomField, "Exc. room:");
		
		addCourseButton = prettyButton("Add Course To Week Plan");

		buttonContainer.getChildren().addAll(
				searchContainer, spacers[0],
				nameContainer, spacers[1],
				schemaContainer, spacers[2],
				websiteContainer, spacers[3],
				audRoomContainer, spacers[4],
				excRoomContainer, spacers[5],
				addCourseButton, spacers[6]
				);

		addIdFieldController();
		addCourseAddController();

	}

	public HBox hContainer(Node node, String labelText) {
		HBox container = new HBox();
		container.setMaxWidth(containerWidth - BUTTON_SIDE_MARGIN);
		Label label = prettyLabel(labelText);
		label.setMinWidth(USE_PREF_SIZE);
		
		Region space = new Region();
		space.setMinWidth(20);
		if (node instanceof TextField) {
			((TextField)node).setPrefWidth(containerWidth - BUTTON_SIDE_MARGIN);
		}
		container.getChildren().addAll(label, space, node);

		
		return container;
	}

	public void addIdFieldController() {
		idField.setOnKeyTyped(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				//If it's the first key pressed - remove text
				if (idField.getText().equals("Enter course id")) {
					idField.setText("");
				}
			}

		});

		idField.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//If field is clicked and there isn't only numbers. Remove text 
				if (!idField.getText().matches("\\d*")){
					idField.setText("");
				}
			}

		});

		idField.setOnKeyReleased(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				KeyCode keyCode = event.getCode();
				if (keyCode == KeyCode.ENTER) {
					//TODO: FIGURE OUT WHY THIS DOESN'T WORK!!!!! IT KEEPS SHOWING!
					if (!alert.isShowing()) {
						String text = idField.getText();
						//Remove everything except digits
						text = text.replaceAll("[^0-9]", "");
						idField.setText(text);
						searchForCourseId(text);
					}
				}
			}

		});

	}

	public void addCourseAddController() {
		
		addCourseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event, boolean overWrite) {
				String name = nameField.getText();
				String courseId = idField.getText();
				String audLocation = audRoomField.getText();
				String excLocation = excRoomField.getText();
				SchemaLocation schemaLocation = SchemaLocation.getSchemaLocation(schemaField.getText());
				String courseWebsite = websiteField.getText();
				Course course = null;
				try {
					course = new Course(name, courseId, audLocation, excLocation, schemaLocation, courseWebsite);
					if (overWrite) {
						Driver.weekPlan.overWriteCoursePos(course, schemaLocation.getLocation());
					} else {
						Driver.weekPlan.addCourse(course, schemaLocation.getLocation(), true);
					}
					Node upperMostChild = Driver.root.getChildren().get(Driver.root.getChildren().size()-1);
					Driver.root.getChildren().get(0).setEffect(null);
					Driver.root.getChildren().remove(upperMostChild);
					Driver.updateWeekContainer(course, schemaLocation.getLocation());
				} catch (URISyntaxException e1) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Invalid URL");
					alert.setHeaderText("That is not a valid URL");
					alert.showAndWait();
					
				} catch (SchemaLocationFilledException e) {
					//TODO: Should actually ask if schema location should be overwritten
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Schema location filled");
					alert.setHeaderText("There is already a course there");
					alert.setContentText("Should the schema location be overwritten?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						handle(event, true);
					} 
				}
			}
			
			@Override
			public void handle(MouseEvent event) {
				handle(event, false);
			}
			
		});
		
	}
	
	private void searchForCourseId(String courseId) {
		try {
			CourseCrawler courseCrawler = new CourseCrawler(courseId);
			websiteField.setText(courseCrawler.getCourseWebsite());
			schemaField.setText(courseCrawler.getSchemaLocation());
			nameField.setText(courseCrawler.getCourseName());
			
		} catch (IOException e) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong!");
			alert.setHeaderText("Couldn't find course in course database");
			alert.showAndWait();
		} catch (CourseIdInvalidException e) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Course ID Error");
			alert.setHeaderText("Course ID should be 5 digits");
			alert.showAndWait();
		} catch (Exception e) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong");
			alert.setHeaderText("Something went wrong fetching data from database");
			alert.showAndWait();
		}

	}
	
	


}
