
class Block {
	BlockColor color;
	Shape blockShape;
	int points[8];
	int orientation;
	
	Block() {
		color = new BlockColor(0);
		shape = new Shape('O', 0);
		points[0] = 0;
		points[1] = 0;
		orientation = 0;
	}
	
	Block(BlockColor inputColor, Shape inputShape, int x, int y, int inputOrientation) {
		color = inputColor;
		blockShape = inputShape;
		orientation = inputOrientation;
		updatePoints(x, y);
	}
	
	int getPoint(int point) {
		return points[point];
	}
	
	void setPoint(int point, int value) {
		points[point] = value;
	}
	
	int getOrientation() {
		return orientation;
	}
	
	void setOrientation(int newOrientation) {
		orientation = newOrientation;
	}
	
	BlockColor getColor() {
		return color;
	}
	
	void setColor(BlockColor newColor) {
		color = newColor;
	}
	
	boolean[][] getShape() {
		return blockShape.getShape();
	}
	
	void setShape(Shape newShape) {
		blockShape = newShape;
	}
	void rotate() {
		int height = blockShape.getHeight();
		int width = blockShape.getWidth();
		int i = 0;
		int j = 0;
		boolean pointFound = false;
		int tempPointX = 0;
		int tempPointY = 0;
		blockShape.setShape(orientation);
		boolean[height][width] tempShape = blockShape.getShape();
		for(i = 0, i < height, i++) {
			for(j = 0, j < width, j++) {
				if(!pointFound) {
					if (tempShape[i][j] == 1) {
						pointFound = true;
						tempPointX = j;
						tempPointY = i;
					}
				}
			}
		}
		updatePoints(tempPointX, tempPointY);
	}
	void rotateRight() {
		orientation++;
		if(orientation == 4) {
			orientation = 0;
		}
		rotate();
	}
	
	void rotateLeft() {
		orientation--;
		if(orientation == -1) {
			orientation = 3;
		}
		rotate();
	}
	
	void moveLeft() {
		if(points[0] > 0 && points[2] > 0 && points[4] > 0 && points[6] > 0) {
			points[0]--;
			points[2]--;
			points[4]--;
			points[6]--;
		}
	}
	
	void moveRight() {
		if(points[0] < 9 && points[2] < 9 && points[4] < 9 && points[6] < 9) {
			points[0]++;
			points[2]++;
			points[4]++;
			points[6]++;
		}
	}
	
	boolean isSameColor(Block otherBlock) {
		return color.isEqual(otherBlock.getColor());
	}
	
	private void updatePoints(int newX, int newY) {
		
	}
}
