import java.util.Arrays;

import javafx.scene.paint.*;

class Block {
	private BlockColor color;
	private Shape shape;
	private int[] points;
	private int orientation;
	
	Block() {
		color = new BlockColor(0);
		shape = new Shape('O', 0);
		points = new int[8];
		points[0] = 0;
		points[1] = 0;
		orientation = 0;
	}
	
	Block(BlockColor inputColor, Shape inputShape, int r, int c, int inputOrientation) {
		color = inputColor;
		shape = inputShape;
		orientation = inputOrientation;
		points = new int[8];
		updatePoints(r, c);
	}
	
	int[] getPoints() {
		return points;
	}
	
	void setPoints(int [] p) {
		points = p;
	}
	
	int getOrientation() {
		return orientation;
	}
	
	void setOrientation(int newOrientation) {
		orientation = newOrientation;
	}
	
	Color getColor() {
		return BlockColor.COLORS[color.getColor()];
	}
	
	void setColor(BlockColor newColor) {
		color = newColor;
	}
	  
	int getColorNum() {
        return color.getColor();
    }
	
	boolean[][] getShape() {
		return shape.getShape();
	}
	
	void setShape(Shape newShape) {
		shape = newShape;
	}
	
	Shape getShapeObj() {
	    return shape;
	}
	
	void rotate() {
	    int[] originalFirstPoint = new int[2];
	    boolean[][] originalShape = shape.getShape();
	    boolean found = false;
	    
	    for(int r = 0; r < originalShape.length; r ++) {
	        for(int c = 0; c < originalShape[0].length; c ++) {
	            if(originalShape[r][c]) {
	                originalFirstPoint[0] = r;
	                originalFirstPoint[1] = c;
	                found = true;
	                break;
	            } // end if
	        } // end for
	        
	        if(found) {
	            break;
	        } // end if
	    } // end for
	    
	    int originRow = points[0] - originalFirstPoint[0]; // first row of the shape array (NOT the first block in the shape)
	    int originCol = points[1] - originalFirstPoint[1]; // first column of the shape array
	    
		int i = 0;
		int j = 0;
		boolean pointFound = false;
		int tempPointX = 0;
		int tempPointY = 0;
		shape.setShape(orientation);
		boolean[][] tempShape = shape.getShape();
		for(i = 0; i < tempShape.length; i++) {
			for(j = 0; j < tempShape[0].length; j++) {
				if(!pointFound) {
					if (tempShape[i][j] == true) {
						pointFound = true;
						tempPointX = i;
						tempPointY = j;
					}
				}
			}
		}
	
		updatePoints(tempPointX + originRow, tempPointY + originCol);
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
		if(points[1] > 0 && points[3] > 0 && points[5] > 0 && points[7] > 0) {
			points[1]--;
			points[3]--;
			points[5]--;
			points[7]--;
		}
	}
	
	void moveRight() {
		if(points[1] < 9 && points[3] < 9 && points[5] < 9 && points[7] < 9) {
			points[1]++;
			points[3]++;
			points[5]++;
			points[7]++;
		}
	}
	
	boolean isSameColor(Block otherBlock) {
		return this.getColorNum() == otherBlock.getColorNum();
	}
	
	public void updatePoints(int newR, int newC) {
		boolean[][] currentShape = shape.getShape();
		int currentPoint = 0;
		for(int i = 0; i < currentShape.length; i++) {
			for(int j = 0; j < currentShape[0].length; j++) {
				if(currentShape[i][j]) {
					points[currentPoint] = i;
					points[currentPoint+1] = j;
					currentPoint = currentPoint + 2;
					
				}
			}
		}

		for(int k = 2; k < 8; k = k + 2) {
		    points[k] = points[k] + newR - points[0];
		    points[k+1] = points[k+1] + newC - points[1];
		}
		
	    points[0] = newR;
	    points[1] = newC;
	}
}
