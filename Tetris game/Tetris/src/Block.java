/**
* Author George Maddux, Laura Guo
* Due Date 12/2/2020
* Class CSCI-3033-001
* 
*/
import java.util.Arrays;

import javafx.scene.paint.*;

class Block {
	private BlockColor color; 	//contains an array of rgb color values with getters and setters
	private Shape shape; 		//contains arrays of hard-coded rotations
	private int[] points; 		//the position of the block on the board
	private int orientation; 	//describes how the block was rotated, for use with Shape
	
	Block() {					//initializes a default block, chosen arbitrarily
		color = new BlockColor(0);
		shape = new Shape('O', 0);
		points = new int[8];
		points[0] = 0;
		points[1] = 0; 
		orientation = 0;
	}
	
	Block(BlockColor inputColor, Shape inputShape, int r, int c, int inputOrientation) {
		//initializes a block based on full input of its criteria
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
	
	void rotate() {	    //driver method for rotating a block
	    int[] originalFirstPoint = getFirstPoint();
	    int originRow = points[0] - originalFirstPoint[0]; // first row of the shape array (NOT the first square in the shape)
	    int originCol = points[1] - originalFirstPoint[1]; // first column of the shape array

		shape.setShape(orientation);
		
		int[] newFirstPoint = getFirstPoint();
		updatePoints(newFirstPoint[0] + originRow, newFirstPoint[1] + originCol); //updates the position of a block using calculated points offset by the original values
	}
	void rotateRight() { //increments the orientation variable modulo 4 then calls rotate
		orientation++;
		if(orientation == 4) {
			orientation = 0;
		}
		rotate();
	}
	
	void rotateLeft() { //decrements the orientation variable modulo 4 then calls rotate
		orientation--;
		if(orientation == -1) {
			orientation = 3;
		}
		rotate();
	}
	
	void moveLeft() {
		points[1]--;
		points[3]--;
		points[5]--;
		points[7]--;
	}
	
	void moveRight() {
		points[1]++;
		points[3]++;
		points[5]++;
		points[7]++;
	}
	
	boolean isSameColor(Block otherBlock) {
		return this.getColorNum() == otherBlock.getColorNum();
	}
	
	public void updatePoints(int newR, int newC) { //takes the new position of the corner of a block's shape array and uses that to calculate the position of each square
		boolean[][] currentShape = shape.getShape();
		int currentPoint = 0;
		for(int i = 0; i < currentShape.length; i++) {
			for(int j = 0; j < currentShape[0].length; j++) {
				if(currentShape[i][j]) { //checks the boolean shape array for if there's a square at that coordinate
					points[currentPoint] = i;
					points[currentPoint+1] = j;
					currentPoint = currentPoint + 2;
					
				}
			}
		}

		for(int k = 2; k < 8; k = k + 2) {
		    points[k] = points[k] + newR - points[0]; //calculates the new location using the old one and an offset
		    points[k+1] = points[k+1] + newC - points[1];
		}
		
	    points[0] = newR;
	    points[1] = newC;
	}
	
	public boolean equals(Block other) { //uses getters to check if another block is identical in type and position on the board
	    boolean sameColor = color.getColor() == other.getColorNum();
	    boolean samePoints = Arrays.equals(points, other.getPoints());
	    boolean sameShape = shape.getLetter() == other.getShapeObj().getLetter();
	    boolean sameOrientation = orientation == other.getOrientation();
	    
	    return sameColor && samePoints && sameShape && sameOrientation;
	}
	
	public int[] getFirstPoint() { //returns the coordinate of the top left point of a block
	    int[] firstPoint = {-1, -1};
        boolean[][] shapeArr = shape.getShape();
        
        for(int r = 0; r < shapeArr.length; r ++) {
            for(int c = 0; c < shapeArr[0].length; c ++) {
                if(shapeArr[r][c]) {
                    firstPoint[0] = r;
                    firstPoint[1] = c;
                    return firstPoint;
                } 
            } 
        } 
        
        return firstPoint;
	}
	
	public int getFirstPointIndex() { //returns the top left point of a block, in the form of an index value based on its shape array
        int currentPoint = 0;
        boolean[][] shapeArr = shape.getShape();
        boolean[][] normalShapeArr = (new Shape(shape.getLetter(), orientation)).getShape();

        for(int r = 0; r < shapeArr.length; r ++) {
            for(int c = 0; c < shapeArr[0].length; c ++) {
                if(shapeArr[r][c]) {
                    
                    return currentPoint;
                } 
                
                else if(normalShapeArr[r][c]) {
                    currentPoint += 2;
                } 
            } 
            
            
        } 
        
        return -1;
	}
	
	public boolean isValidShapePoint(int r, int c) { //returns whether the coordinate is a square of the shape on the board
	    int[] firstPoint = getFirstPoint();
	    int firstPointIndex = getFirstPointIndex();
	    
	    if(firstPointIndex == -1) { // no first point; blank block
	        return false;
	    } // end if
	    
	    int[] originPoint = {points[firstPointIndex] - firstPoint[0], points[firstPointIndex + 1] - firstPoint[1]}; // the location on the board where the 0,0 in the shape array is at
	    
	    int shapeRow = r - originPoint[0];
	    int shapeCol = c - originPoint[1]; 

	    return shape.getShape()[shapeRow][shapeCol]; 
	}
	
	public boolean isBlankBlock() { //returns whether every square of the block has been cleared
	    boolean isBlankBlock = true;
        boolean[][] shapeArray = shape.getShape();
        
        for(int x=0; x<shapeArray.length; x++) {
            for(int y=0; y<shapeArray[x].length; y++) {
                if(shapeArray[x][y] == true) {
                    isBlankBlock = false;
                }
            }
        }
        
        return isBlankBlock;
	}
}
