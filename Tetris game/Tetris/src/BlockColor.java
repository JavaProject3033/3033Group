import javafx.scene.paint.Color;

public class BlockColor {
	public static final NUM_COLORS = 7;
	public static final Color COLORS[NUM_COLORS];
	private static Color currentColors[];
	private int color;
	
	BlockColor() {
		color = 0;
		COLORS[0] = new Color(250, 50, 10); // red
		COLORS[1] = new Color(10, 250, 50); // green
		COLORS[2] = new Color(10, 50, 250); // blue
		COLORS[3] = new Color(250, 160, 10); // orange
		COLORS[4] = new Color(190, 20, 220); // purple
		COLORS[5] = new Color(20, 230, 230); // teal
		COLORS[6] = new Color(250, 250, 0); // yellow
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
