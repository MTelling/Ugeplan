package dk.tellings.app.frontend;

import java.util.ArrayList;
import java.util.List;

import dk.tellings.app.backend.Course;
import dk.tellings.app.backend.WeekPlan;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WeekContainer extends HBox{
	
	private String[] dayList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	private List<CourseBox> courseBoxes;
	
	public WeekContainer(WeekPlan weekPlan) {
		this.courseBoxes = new ArrayList<CourseBox>();
		
		for (Course course: weekPlan.getCourseList()) {
			courseBoxes.add(new CourseBox(course));
		}
		
		for (int i = 0; i < 5; i++) {
			VBox day = dayContainer(i);
			this.getChildren().add(day);
		}
	}
	
	/**
	 * Creates a vertical box with the name of the day at top. Then a course, another label and last another course.
	 * @param dayIndex
	 * @return VBox day
	 */
	public VBox dayContainer(int dayIndex) {
		VBox day = new VBox();
		
		day.getChildren().add(prettyLabel(dayList[dayIndex]));
		day.getChildren().add(courseBoxes.get(dayIndex*2));
		day.getChildren().add(prettyLabel("PAUSE"));
		day.getChildren().add(courseBoxes.get(dayIndex*2+1));
		
		
		return day;
	}
	
	/**
	 * Makes a pretty label with the text given in parameter
	 * @param text
	 * @return StackPane prettyLabel
	 */
	public StackPane prettyLabel(String text) {
		StackPane prettyLabel = new StackPane();
		Label label = new Label(text);
		prettyLabel.getChildren().add(label);
		prettyLabel.setPadding(new Insets(5,0,5,0));;
		
		return prettyLabel;
	}
}
