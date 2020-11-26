import java.util.*;

public class Board 
{
    public final static int ROWS = 20;
    public final static int COLS = 10;
    final int MAX_BLOCKS = ROWS * COLS / 4;
    boolean board[][];
    Block blocks[];
    int blocksLength;
    Block futureBlocks[];
    double normalBlockSpeed;
    double dropBlockSpeed;
    int squareSideLength;
    int currentBlockIndex;
    Block current;
    
    Board()
    {
       /*
        Initialize board[][] with zeros
	Initialize blocks[]
	Generate/initialize futureBlocks
	Initialize normalBlockSpeed to 0
	Initialize dropBlockSpeed to 0
	Initialize squareSideLength to 0
	Initialize current to futureBlocks[0]
        */ 
        board = new boolean[ROWS][COLS];
        blocks = new Block[MAX_BLOCKS];
        blocksLength = 0;
        futureBlocks = new Block[Shape.NUM_SHAPES];
        generateNewFutureBlocks();
        normalBlockSpeed = 0;
        dropBlockSpeed = 0;
        squareSideLength = 0;
        current = futureBlocks[0];
        addBlock(current); //TODO
        currentBlockIndex = 0;
    }
    
    Board(int normalSpeed, int dropSpeed, int sideLen)
    {
        /*
        Initialize board[][] with zeros
        Initialize blocks[]
        Generate/initialize futureBlocks
        Initialize normalBlockSpeed to the given argument
        Initialize dropBlockSpeed to the given argument
        Initialize squareSideLength to the given argument
        */
        board = new boolean[ROWS][COLS];
        blocks = new Block[MAX_BLOCKS];
        blocksLength = 0;
        futureBlocks = new Block[Shape.NUM_SHAPES];
        generateNewFutureBlocks();
        normalBlockSpeed = normalSpeed;
        dropBlockSpeed = dropSpeed;
        squareSideLength = sideLen;
        current = futureBlocks[0];
        currentBlockIndex = 0;
    }
    
    void clearRow()
    {
        /*
        Checks each row and clears them if needed by setting the cleared portions in the shape to be false
	May be helpful to use moveBlocksDown()
	Use this method when block hits the other stationary blocks
        */
        
        Block[][] locations = generateBlockLocations();
        
        for(int i=0; i<ROWS; i++) {
            //check for row with all true
            boolean allFilled = true;
            
            for(int j=0; j<COLS; j++){
                if(board[i][j] == false){
                    allFilled = false;
                    break;
                }
            }
            
            if(allFilled){
                for(int j=0; j<COLS; j++){
                    board[i][j] = false;
                    
                    Block current = locations[i][j];

                    // change the parts of the block shapes to false
                    int[] firstPointInShape = current.getFirstPoint();
                    int[] firstPoints = {current.getPoints()[current.getFirstPointIndex()], current.getPoints()[current.getFirstPointIndex() + 1]};
                    int[] shapeArrOrigin = {firstPoints[0] - firstPointInShape[0], firstPoints[1] - firstPointInShape[1]}; // find the location of row 0 and col 0 of the shape on the board
                    current.getShape()[i - shapeArrOrigin[0]][ j - shapeArrOrigin[1]] = false; // for each point referred to by i and j, find the row/col in the shape array and set it to false
                    
                    // TODO not modifying the points array; might be an issue . . . (esp. since there isn't a good way to signify
                        // that there is no point without breaking other parts . . . 
                    
                  System.out.println(i + " " + j + "\n" + current.getShapeObj());

                }
            }
        }
        
        System.out.println("in method\n" + toString());
        
        moveBlocksDown();
    }
    
    void clearColors()
    {
        // Board is array of boolean. how to check the color
        // 
        
        /*
        Checks for and clears color clusters
	May be helpful to use moveBlocksDown()
	Use this method when block hits the other stationary blocks
        */
        
        Block[][] locations = generateBlockLocations();
        
        // for each block in the array
        for(int r = 0; r < ROWS; r ++) {
            for(int c = 0; c < COLS; c ++) {
                Block current = locations[r][c];
                
                if(current == null)
                    continue; // skip the rest of this iteration
                
                // getting neighbors
                Block[] neighbors = getNeighbors(current);
                
                // counting the number of neighbors with the same color as the current block
                int blockColor = current.getColorNum();
                Block[] sameColorBlocks = new Block[neighbors.length];
                int numSameColor = 0;
                
                for(int i = 0; i < neighbors.length; i ++) {
                    if(neighbors[i] == null)
                        break;
                    
                    if(blockColor == neighbors[i].getColorNum()) {
                        sameColorBlocks[numSameColor] = neighbors[i];
                        numSameColor ++;
                    } // end if
                } // end for
                
                // removing those blocks from the blocks array and from the board
                if(numSameColor >= 2) { // this block has at least two touching it that are the same color means there are three blocks together with the same color
                    removeBlock(current);
                    
                    for(int i = 0; i < numSameColor; i ++) {
                        removeBlock(sameColorBlocks[i]);
                    } // end for
                } // end if
            } // end for
        } // end for
        
        moveBlocksDown();
    }
    
    void nextBlock()
    {
        /*
        Places the next block into the current variable
	Increments the currentBlockIndex variable
	Calls on generateNewFutureBlocks when needed
        */
        currentBlockIndex++;
        
        if (currentBlockIndex > futureBlocks.length -1) {
            currentBlockIndex = 0;
            generateNewFutureBlocks();
            
        } 
       
        current = futureBlocks[currentBlockIndex];
    }
    
    boolean isValidLocation(int x, int y)
    {      
        /*
        Checks to see if a point is on the board
	Use this to check if a userâ€™s move would be valid or not
        */    
        if(x<0 || x>=ROWS || y<0 || y>=COLS)
            return false;
        else
            return true;
    }
    
    void generateNewFutureBlocks()
    {
        /*
        Generates new blocks for the futureBlocks array.
	To make the game play more balanced:
        	All shapes should be used at least once
        	All colors should be used at least once
        	The order and color-block assignment should be random
        */
        Random randShape = new Random();
        Random randPoint = new Random();
        Random randOrientiation = new Random();
        
        ArrayList<Shape> shapeList= new ArrayList<Shape>();
        shapeList.add(new Shape('o', randShape.nextInt(4)));
        shapeList.add(new Shape('i', randShape.nextInt(4)));
        shapeList.add(new Shape('s', randShape.nextInt(4)));
        shapeList.add(new Shape('z', randShape.nextInt(4)));
        shapeList.add(new Shape('l', randShape.nextInt(4)));
        shapeList.add(new Shape('j', randShape.nextInt(4)));
        shapeList.add(new Shape('t', randShape.nextInt(4)));
        
        ArrayList<BlockColor> blockColourList = new ArrayList<BlockColor>();
        blockColourList.add(new BlockColor(0));
        blockColourList.add(new BlockColor(1));
        blockColourList.add(new BlockColor(2));
        blockColourList.add(new BlockColor(3));
        blockColourList.add(new BlockColor(4));
        blockColourList.add(new BlockColor(5));
        blockColourList.add(new BlockColor(6));
        
        Collections.shuffle(shapeList);
        Collections.shuffle(blockColourList);
        
        for(int i=0; i<futureBlocks.length; i++)
        {
            futureBlocks[i] = new Block(blockColourList.get(i), shapeList.get(i),
                                    randPoint.nextInt(20), randPoint.nextInt(20), 
                                    randOrientiation.nextInt(4));
        }
    }
    
    void moveBlocksDown()
    {
        /*
        Checks to make sure that there are no blocks that are randomly floating
        If a block is not touching the bottom or attached somewhere to another 
        block, it should â€œfallâ€� down until it is resting on something
        */
        
        boolean[][] newBoard = new boolean[ROWS][COLS];
        
        for(int i = 0; i < blocksLength; i ++) {
            int[] points = blocks[i].getPoints();
            
            while(!isAtBottom(blocks[i])) {
                for(int j = 0; j < points.length; j += 2) {
                    points[j] ++;
                } // end for
            } // end while
            
            for(int j = 0; j < points.length; j += 2) {
                if(isValidLocation(points[j], points[j + 1]) && blocks[i].isValidShapePoint(points[j], points[j + 1])) {
                    newBoard[points[j]][points[j + 1]] = true;
                } // end if
            } // end for
        } // end for
        
        board = newBoard;
    }    
    
    void removeBlankBlocks()
    {
        /*
            Checks the blocks array and removes any blocks whose shapes are 
            completely false 
        */   
        boolean validShape = false;  
        ArrayList<Block> validBlocks = new ArrayList<Block>();
        
        for(int i=0; i<blocksLength; i++) {
            validShape = false;
            Shape shape = blocks[i].getShapeObj();
            boolean[][] shapeArray = shape.getShape();
            
            for(int x=0; x<shapeArray.length; x++) {
                for(int y=0; y<shapeArray[x].length; y++) {
                    if(shapeArray[x][y] == true) {
                        validShape = true;
                    }
                }
            }
            
            if(validShape)
                validBlocks.add(blocks[i]);
        }
        
        blocks = validBlocks.toArray(blocks);
        blocksLength = validBlocks.size();
    }
        
    void addBlock(Block b) {
        blocks[blocksLength] = b;
        blocksLength ++;
        
        // making sure the add is reflected in the board array
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            int r = points[i];
            int c = points[i + 1];

            board[r][c] = true;
        } // end for
    } // end addBlock
    
    void removeBlock(Block b) {
        // making sure the remove is reflected in the board array
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            int x = points[i];
            int y = points[i + 1];
            
            board[x][y] = false;
        } // end for
        
        // puts the last block in place of the block to be removed, then decreases the length of the array
        for(int i = 0; i < blocksLength; i ++) {
            if(blocks[i].equals(b)) { // found the block to remove
                blocks[i] = blocks[blocksLength - 1];
                blocks[blocksLength - 1] = null;
                blocksLength --;
                break;
            } // end if
        } // end for
    } // end removeBlock
    
    void removeBlock(int x, int y) {
        // finding the block to remove
        for(int i = 0; i < blocksLength; i ++) {
            Block current = blocks[i];
            int[] points = current.getPoints();
            
            if(points[0] == x && points[1] == y) { 
                // removing it via the index it is in the array
                removeBlock(i);
                break;
            } // end if
        } // end for
    } // end removeBlock
    
    void removeBlock(int index) {
        Block b = blocks[index];
        
        // making sure the remove is reflected in the board array
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            int x = points[i];
            int y = points[i + 1];
            
            board[x][y] = false;
        } // end for
        
        // puts the last block in place of the block to be removed, then decreases the length of the array
        blocks[index] = blocks[blocksLength - 1];
        blocks[blocksLength - 1] = null;
        blocksLength --;
    } // end removeBlock
    
    public Block[][] generateBlockLocations() { 
        Block[][] locations = new Block[ROWS][COLS];
        
        // goes through each block and puts them on the locations board
        for(int i = 0; i < blocksLength; i ++) {
            Block current = blocks[i];
            int[] points = current.getPoints();
            
            for(int j = 0; j < points.length; j += 2) {
                if(isValidLocation(points[j], points[j + 1]) && current.isValidShapePoint(points[j], points[j + 1])) {
                    locations[points[j]][points[j + 1]] = current; 
                } // end if
            } // end for
        } // end for
        
        return locations;
    } // end generateBlockLocations
    
    public Block[] getNeighbors(Block b) {
        Block[] neighbors = new Block[12]; // 12 is the maximum number of neighbors it probably has (rough guess)
        int neighborsIndex = 0;
        Block[][] locations = generateBlockLocations();
        int[] points = b.getPoints();
        
        for(int i = 0; i < points.length; i += 2) {
            int r = i;
            int c = i + 1;
            int[] possibleNeighborsPoints = {
                    points[r], points[c] - 1, 
                    points[r] + 1, points[c], 
                    points[r], points[c] + 1, 
                    points[r] - 1, points[c]};
            System.out.println("POINT " + b.getShapeObj().getLetter() + " " + r + " " + c);
            
            // a neighbor is any block around any point that is not the same block . . .
            for(int j = 0; j < possibleNeighborsPoints.length; j += 2) {
                if(this.isValidLocation(possibleNeighborsPoints[j], possibleNeighborsPoints[j + 1])) {
                    Block current = locations[possibleNeighborsPoints[j]][possibleNeighborsPoints[j + 1]];
                    System.out.println("POTENTIAL NEIGHBOR: " + current);
                    
                    if(current != null && 
                            current != b && 
                            current.isValidShapePoint(possibleNeighborsPoints[j], possibleNeighborsPoints[j + 1]) && 
                            board[possibleNeighborsPoints[j]][possibleNeighborsPoints[j + 1]]) {
                        boolean alreadyIncluded = false;
                        
                        // . . . or a block that is already counted as a neighbor
                        for(int k = 0; k < neighborsIndex; k ++) {
                            if(current == neighbors[k]) {
                                alreadyIncluded = true;
                                System.out.println("already included");
                                break;
                            } // end if
                        } // end for
                        
                        if(!alreadyIncluded) {
                            neighbors[neighborsIndex] = current;
                            neighborsIndex ++;
                            System.out.println("NEIGHBOR");
                        } // end if
                    } // end if
                } // end if
            } // end for
        } // for
        
        return Arrays.copyOf(neighbors, neighborsIndex);
    } // end getNeighbors
    
    public boolean isAtBottom(Block b) {
        for(int i = 0; i < b.getPoints().length; i += 2) {
            int currentR = b.getPoints()[i];
            int currentC = b.getPoints()[i + 1];
            
            // this is a valid shape point 
            if(b.isValidShapePoint(currentR, currentC)) {
                System.out.println("VALID: " + b.getShapeObj().getLetter() + " " + currentR + " " + currentC);
                
                if (currentR == ROWS - 1) { // at the bottom of the board
                    System.out.println("VALID+AT BOTTOM: " + b.getShapeObj().getLetter() + " " + currentR + " " + currentC);
                    return true;
                } // end if
                    
                // there is a valid square below it on the board (or the board bottom)
                Block[][] locations = generateBlockLocations();
                Block below = locations[currentR + 1][currentC];
                System.out.println("this: " + b + " below: " + below);

                if(below != null) System.out.println("Below valid: " + below.isValidShapePoint(currentR + 1, currentC));
                System.out.println("below valid on board: " + board[currentR + 1][currentC] );
                if (below != null && board[currentR + 1][currentC] && below.isValidShapePoint(currentR + 1, currentC) && below != b) {
                    System.out.println("VALID+AT BOTTOM: " + b.getShapeObj().getLetter() + " " + currentR + " " + currentC);
                    return true;
                } // end if
                
            } // end if
            
            else {
                System.out.println("INVALID: " + b.getShapeObj().getLetter() + " " + currentR + " " + currentC);
            }
            System.out.println("");
         } // end for
        
        return false;
    } // end isAtBottom
    
    // getters/setters for non-final variables

    public boolean[][] getBoard() {
        return board;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Block[] getFutureBlocks() {
        return futureBlocks;
    }

    public double getNormalBlockSpeed() {
        return normalBlockSpeed;
    }

    public double getDropBlockSpeed() {
        return dropBlockSpeed;
    }

    public int getSquareSideLength() {
        return squareSideLength;
    }

    public int getCurrentBlockIndex() {
        return currentBlockIndex;
    }

    public Block getCurrent() {
        return current;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public void setFutureBlocks(Block[] futureBlocks) {
        this.futureBlocks = futureBlocks;
    }

    public void setNormalBlockSpeed(double normalBlockSpeed) {
        this.normalBlockSpeed = normalBlockSpeed;
    }

    public void setDropBlockSpeed(double dropBlockSpeed) {
        this.dropBlockSpeed = dropBlockSpeed;
    }

    public void setSquareSideLength(int squareSideLength) {
        this.squareSideLength = squareSideLength;
    }

    public void setCurrentBlockIndex(int currentBlockIndex) {
        this.currentBlockIndex = currentBlockIndex;
    }

    public void setCurrent(Block current) {
        this.current = current;
    }    
    
    public int getBlocksLength() {
        return blocksLength;
    }
    
    public String toString() {
        String boardStr = "";
        
        for(int r = 0; r < ROWS; r ++) {
            for(int c = 0; c < COLS; c ++) {
                if(board[r][c]) {
                    boardStr += "# ";
                }
                
                else {
                    boardStr += ". ";
                }
            }
            
            boardStr += "\n";
        }
        
        return boardStr;
    }
}
