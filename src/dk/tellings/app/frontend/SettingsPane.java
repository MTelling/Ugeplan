package dk.tellings.app.frontend;

import dk.tellings.app.Driver;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;

public class SettingsPane extends Presenter{
	
	private static final double BLUR_AMOUNT = 3;

    private static final Effect frostEffect =
        new BoxBlur(BLUR_AMOUNT, BLUR_AMOUNT, 3);
    
    private Button addCourseButton;
    
	public SettingsPane() {
		super(2);

		//Create UI
		addCourseButton = prettyButton("Add course");
		Label titleLabel = prettyLabel("Settings");
		
		//Add blur to background
		Driver.root.getChildren().get(0).setEffect(frostEffect);

		//Add everything to buttoncontainer
		buttonContainer.getChildren().addAll(
				titleLabel, spacers[0],
				addCourseButton, spacers[1]
						);

		
		//Add listeneer
		addListenerToCourseButton();
	}
	
	/**
	 * Listen for click on addCourseButton. If it's clicked, present the add course pane. 
	 */
	public void addListenerToCourseButton() {
		addCourseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Node upperMostNode = Driver.root.getChildren().get(Driver.root.getChildren().size()-1);
				Driver.root.getChildren().remove(upperMostNode);
				Driver.root.getChildren().add(new AddCoursePane());
			}
			
		});
	}
}
