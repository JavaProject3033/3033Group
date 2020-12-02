/**
* Author George Maddux
* Due Date 12/2/2020
* Class CSCI-3033-001
* defines and provides the color values the blocks will be displayed with
*/
import javafx.scene.paint.Color;

public class BlockColor {
	public static final int NUM_COLORS = 7;
	public static final Color COLORS[] = {
	        Color.rgb(250, 50, 10), // red
	        Color.rgb(10, 250, 50), // green
	        Color.rgb(10, 50, 250), // blue
	        Color.rgb(250, 160, 10), // orange
	        Color.rgb(190, 20, 220), // purple
	        Color.rgb(20, 230, 230), // teal
	        Color.rgb(250, 250, 0) // yellow
	};
	
	private int color;
	
	BlockColor() {
		color = 0;
	}
	
	BlockColor(int chosenColor) {
		color = chosenColor;
	}
	
	boolean isEqual(BlockColor otherColor) {
		return otherColor.getColor() == color;
	}
	
	void setColor(int newColor) {
		color = newColor;
	}
	
	int getColor() {
		return color;
	}
}
