package dk.tellings.app.frontend;

import dk.tellings.app.Driver;
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

public abstract class Presenter extends StackPane{
	private static final double BLUR_AMOUNT = 3;
	protected double containerWidth;
	public static final double TOP_BOT_MARGIN = 100;
	public static final double BUTTON_SIDE_MARGIN = 50;
	protected Region[] spacers;
	protected VBox buttonContainer;

	private static final Effect frostEffect =
			new BoxBlur(BLUR_AMOUNT, BLUR_AMOUNT, 3);


	public Presenter(int numOfSpacers) {
		this(numOfSpacers, 300);
	}
	
	public Presenter(int numOfSpacers, double containerWidth) {
		this.containerWidth = containerWidth;
		
		//Init buttoncontainer
		buttonContainer = new VBox();
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.setId("coursePresenter");
		buttonContainer.setMaxHeight(Driver.root.getHeight() - TOP_BOT_MARGIN);
		buttonContainer.setMaxWidth(containerWidth);

		//Create needed spacers
		spacers = new Region[numOfSpacers];
		for (int i = 0; i < spacers.length; i++) {
			spacers[i] = new Region();
			VBox.setVgrow(spacers[i], Priority.ALWAYS);
		}

		//Create close button
		HBox closeButtonContainer = closeButtonContainer();
		buttonContainer.getChildren().addAll(closeButtonContainer);
		
		//Add blur to background
		Driver.root.getChildren().get(0).setEffect(frostEffect);
		
		//Add buttoncontainer to stackpane. 
		this.getChildren().add(buttonContainer);
	}
	


	private HBox closeButtonContainer() {
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
	
	protected Button prettyButton(String text) {
		Button prettyButton = new Button(text);
		prettyButton.setPrefSize(containerWidth - BUTTON_SIDE_MARGIN, USE_COMPUTED_SIZE);
		
		return prettyButton;
	}
	
	protected Label prettyLabel(String text) {
		Label prettyLabel = new Label(text);
		prettyLabel.setWrapText(true);
		prettyLabel.setTextAlignment(TextAlignment.CENTER);
		
		prettyLabel.setId("coursePresenterLabel");
		
		return prettyLabel;
	}
}
