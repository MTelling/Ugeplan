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
	public static final InnerShadow HOVER_SHADOW = new InnerShadow(8, Color.BLACK);
	private Label courseNameLabel;
	
	public CourseBox(Course course) {
				
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(Driver.COURSEBOX_WIDTH, Driver.COURSEBOX_HEIGHT);
		this.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
		
		//TODO: Temporary style - should be css. 
		this.setStyle("-fx-background-color: #FFFFFF;");		
		
		this.init(course);

	}
	
	/**
	 * Adds labels according to a given course and activates event handlers
	 * @param course
	 */
	public void init(Course course) {
		this.course = course;
		this.courseNameLabel = defaultLabel();
		this.courseNameLabel.setText(course.getName());
		this.getChildren().add(courseNameLabel);	
		
		if (!course.getCourseId().isEmpty()) {
			Label courseIdLabel = defaultLabel();
			courseIdLabel.setText("("+course.getCourseId()+")");
			this.getChildren().add(courseIdLabel);
			
			setMouseClickEvent();
			setMouseHover();
		}
	}

	/**
	 * Removes all labels and event handlers
	 */
	public void reset() {
		this.getChildren().clear();
		//Remove event handlers 
		this.setOnMouseClicked(null);
		this.setOnMouseEntered(null);
		this.setOnMouseExited(null);
		//Sets look back to default so it doesn't stay "hovered"
		this.setEffect(null);
		this.setCursor(Cursor.DEFAULT);
	}
	
	/**
	 * @param text
	 * @return a label with a given text that will always keep the text centered.
	 */
	private Label defaultLabel() {
		Label label = new Label();
		
		label.setWrapText(true);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setAlignment(Pos.CENTER);
		
		return label;
	}

	
	///// GETTTERS AND SETTERS FROM HERE /////
	
	public Course getCourse() {
		return course;
	}
	
	///// CONTROLLER FROM HERE /////

	/**
	 * Determines what should happen when a course box is clicked. 
	 */
	private void setMouseClickEvent() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//Add a new coursepresenter for the clicked course
				Driver.root.getChildren().add(new CoursePresenter(course));
		
			}
			
		});
	}
	
	/**
	 * Creates the pretty shadow and changes cursor when mouse hovers over a box. 
	 */
	private void setMouseHover() { 
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(HOVER_SHADOW);
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
}
