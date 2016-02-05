package dk.tellings.app.frontend;


import dk.tellings.app.Driver;
import dk.tellings.app.backend.Course;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class CourseBox extends VBox{
	
	private Course course;
	private InnerShadow hoverShadow;
	
	public CourseBox(Course course) {
		
		this.course = course;
		createShadow();
		
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(Driver.COURSEBOX_WIDTH, Driver.COURSEBOX_HEIGHT);
		this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		
		//TODO: Temporary style - should be css. 
		this.setStyle("-fx-background-color: #FFFFFF;");
		
		//Create labels for the name of the course and id of the course. 
		Label courseNameLabel = defaultLabel(course.getName());
		Label courseIdLabel = defaultLabel("("+course.getCourseId()+")");
	
		this.getChildren().addAll(courseNameLabel, courseIdLabel);
		
		setMouseClickEvent();
		setMouseHover();
	}
	
	/**
	 * @param text
	 * @return a label with a given text that will always keep the text centered.
	 */
	private Label defaultLabel(String text) {
		Label label = new Label(text);
		
		label.setWrapText(true);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setAlignment(Pos.CENTER);
		
		return label;
	}
	
	/**
	 * Determines what should happen when a course box is clicked. 
	 */
	private void setMouseClickEvent() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("You clicked " + course.getCourseId());
			}
			
		});
	}
	
	/**
	 * Creates the pretty shadow and changes cursor when mouse overs over a box. 
	 */
	private void setMouseHover() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(hoverShadow);
				setCursor(Cursor.HAND);
			}
			
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				setCursor(Cursor.DEFAULT);
			}
			
		});
	}
	
	/**
	 * Creates the shadow effect so it won't have to be recreated at each hover event. 
	 */
	private void createShadow() {
		this.hoverShadow = new InnerShadow(8, Color.BLACK);
	}
	
}
