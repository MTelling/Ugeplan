package dk.tellings.app.frontend;

import dk.tellings.app.Driver;
import dk.tellings.app.backend.Course;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class CoursePresenter extends StackPane {
	
	private static final double BLUR_AMOUNT = 3;

    private static final Effect frostEffect =
        new BoxBlur(BLUR_AMOUNT, BLUR_AMOUNT, 3);

	public CoursePresenter(Course course) {
		//Init buttoncontainer
		VBox buttonContainer = new VBox();
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.setId("coursePresenter");
		buttonContainer.setMaxHeight(Driver.root.getHeight() - 100);
		buttonContainer.setMaxWidth(300);
		
		//Create needed spacers
		Region[] spacers = new Region[5];
		for (int i = 0; i < spacers.length; i++) {
			spacers[i] = new Region();
			VBox.setVgrow(spacers[i], Priority.ALWAYS);
		}
		
		
		//Create UI
		HBox closeButtonContainer = closeButtonContainer();
		Label courseNameLabel = prettyLabel(course.getName());
		Label courseIdLabel = prettyLabel("("+course.getCourseId()+")");
		Label courseAudLabel = prettyLabel(course.getAudLocation());
		Label courseExcLabel = prettyLabel(course.getExcLocation());
		Button goToWebsite = new Button("Go to course website");
		
		//Add blur to background
		Driver.root.getChildren().get(0).setEffect(frostEffect);
		
		//Add everything to buttoncontainer
		buttonContainer.getChildren().addAll(closeButtonContainer, spacers[0],
				courseNameLabel,
				courseIdLabel, spacers[1],
				courseAudLabel, spacers[2],
				courseExcLabel, spacers[3],
				goToWebsite, spacers[4]);
		
		//Add buttoncontainer to stackpane. 
		this.getChildren().add(buttonContainer);
	}
	
	public Label prettyLabel(String text) {
		Label prettyLabel = new Label(text);
		prettyLabel.setWrapText(true);
		prettyLabel.setTextAlignment(TextAlignment.CENTER);
		
		prettyLabel.setId("coursePresenterLabel");
		
		return prettyLabel;
	}
	
	public HBox closeButtonContainer() {
		//Create close icon
		HBox closeButtonContainer = new HBox();
		closeButtonContainer.setPadding(new Insets(3, 3, 0, 0));
		Region hSpace = new Region();
		HBox.setHgrow(hSpace, Priority.ALWAYS);
		Image closeIcon = new Image(""+this.getClass().getResource("Close-icon.png"), true);
		ImageView iV = new ImageView();
		iV.setFitHeight(25);
		iV.setFitWidth(25);
		iV.setImage(closeIcon);
		iV.setSmooth(true);
		iV.setCache(true);
		closeButtonContainer.getChildren().addAll(hSpace, iV);

		//Close when close icon clicked
		closeButtonContainer.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				Driver.root.getChildren().get(0).setEffect(null);
				Driver.root.getChildren().remove(Driver.root.getChildren().size() - 1);			
			}
			
		});


		closeButtonContainer.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(Cursor.HAND);
			}

		});

		closeButtonContainer.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(Cursor.DEFAULT);
			}

		});
		return closeButtonContainer;
	}
}
