package dk.tellings.app.frontend;

import java.io.IOException;

import dk.tellings.app.backend.Course;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CoursePresenter extends Presenter {
	
	private Button goToWebsite;
	private Course course;

	public CoursePresenter(Course course) {
		super(5);
		
		this.course = course;
		
		//Create UI
		Label courseNameLabel = prettyLabel(course.getName());
		Label courseIdLabel = prettyLabel("("+course.getCourseId()+")");
		Label courseAudLabel = prettyLabel(course.getAudLocation());
		Label courseExcLabel = prettyLabel(course.getExcLocation());
		goToWebsite = prettyButton("Go to course website");
		
		
		//Add everything to buttoncontainer
		buttonContainer.getChildren().addAll(
				spacers[0],
				courseNameLabel,
				courseIdLabel, spacers[1],
				courseAudLabel, spacers[2],
				courseExcLabel, spacers[3],
				goToWebsite, spacers[4]);
		
		
		//Add listener to button
		addListenerToWebsiteButton();
		
	}
	
	
	private void addListenerToWebsiteButton() {
		goToWebsite.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					course.goToWebsite();
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error opening website");
					alert.setHeaderText("Couldn't open website.");
					alert.setContentText(e.getMessage());

					alert.showAndWait();
				}
				
			}
			
		});
	}
	

}
