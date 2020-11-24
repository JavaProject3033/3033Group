
public class Shape {
	private int height;
	private int width;
	private char currentShape;
	
    final static boolean [][][] O = {    {{true, true},
                                         {true, true}},

                                        {{true, true},
                                    	 {true, true}},

                                    	 {{true, true},
                                    	  {true, true}},

                                    	  {{true, true},
                                    	   {true, true}}
                                    	};
    final static boolean [][][] I = {{{false, false, false, false},
    		                          {true, true, true, true},
    		                          {false, false, false, false},
    		                          {false, false, false ,false}},

    		                         {{false, false, true, false},
    		                          {false, false, true, false},
    		                          {false, false, true, false},
    		                          {false, false, true, false}},

    		                          {{false, false, false, false},
    		                           {false, false, false, false},
    		                           {true, true, true, true},
    		                           {false, false, false, false}},

    		                            {{false, true, false, false},
    		                             {false, true, false, false},
    		                             {false, true, false, false},
    		                             {false, true, false, false}}
    		                         };

          final static boolean [][][] L = {{{false, false, true},
        		                           {true, true, true},
        		                           {false, false, false}},

        		                           {{false, true, false},
        		                            {false, true, false},
        		                            {false, true, true}},

        		                           {{false, false, false},
        		                             {true, true, true},
        		                             {true, false, false}},

        		                             {{true, true, false},
        		                              {false, true, false},
        		                              {false, true, false}},
 
        		                          };
          final static boolean [][][] S = { {{false, true, true},
        		                            {true, true, false},
        		                            {false, false, false}},

        		                            {{false, true, false},
        		                             {false, true, true},
        		                             {false, false, true}},

        		                             {{false, false, false},
        		                              {false, true, true},
        		                              {true, true, false}},

        		                              {{true, false, false},
        		                               {true, true, false},
        		                               {false, true,false}}
        		                               };
          final static boolean [][][] Z = {{{true, true, false},
        		                           {false, true, true},
        		                           {false, false, false}},

        		                            {{false, false, true},
        		                            {false, true, true},
        		                            {false, true, false}},

        		                            {{false, false, false},
        		                            {true, true, false},
        		                            {false, true, true}},

        		                            {{false, true, false},
        	                           	    {true, true, false},
        		                            {true, false, false}}
        		                            };
          final static boolean [][][]  J= {{{true, false, false},
        		                          {true, true, true},
        		                          {false, false, false}},

        		                          {{false, true, true},
        		                           {false, true, false},
        		                           {false, true, false}},

        		                           {{false, false, false},
        		                           {true, true, true},
        		                           {false, false, true}},

        		                           {{false, true, false},
        		                            {false, true, false},
        		                            {true, true, false}},
        		                           };
          final static boolean [][][] T= {{{false, true, false},
        		                           {true, true, true},
                                           {false, false, false}},

        		                           {{false, true, false},
        		                           {false, true, true},
        		                           {false, true, false}},

        		                            {{false, false, false},
        		                            {true, true, true},
        		                            {false, true, false}},

        		                            {{false, true, false},
        		                             {true, true, false},
        		                             {false, true, false}}
        		                             };

       	public final static int NUM_SHAPES = 7;
       	private boolean [][] shape;
       	
       	private final static String shapeLetters = "OILSZJT"; // used in combination with shapes[] to access the shapes more easily
       	private final static boolean[][][][] shapes = {O, I, L, S, Z, J, T};

                   
	public Shape()
	{
	    this('O', 0);
		
	}
	public Shape(char shape, int orientation)
	{
	    // initializing width and height variables
	    int[] dimensions = getDimensions(shape, orientation);
        height = dimensions[0];
        width = dimensions[1];
        
        // initializing shape array variables
        int shapesIndex = shapeLetters.indexOf(Character.toUpperCase(shape));
        int numRows = shapes[shapesIndex][0].length;
        int numCols = shapes[shapesIndex][0][0].length;
        
	    this.shape = new boolean[numRows][numCols];
		setShape(shape, orientation);
	}
	private void arraycopy2D(boolean[][] src, int srcPos, boolean[][]  dst,int destPos, int length)
	{
		
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], srcPos, dst[i],destPos, src[i].length);
	    }
	}
	
	private static int[] getDimensions(char shape, int orientation) {
	    int[] dimensions = {0, 0};
	    boolean[][] shapeArr = shapes[shapeLetters.indexOf(Character.toUpperCase(shape))][orientation]; // getting the shape/orientation that we want to find the dimensions of
	    int shapeStart = -1; // index of the start of the shape's width or height 
	    int shapeEnd = -1; // index of the end of the shape's width or height
	    
	    // determining the height of the given shape
	    for(int r = 0; r < shapeArr.length; r ++) {
	        for(int c = 0; c < shapeArr[0].length; c ++ ) {
	            if(shapeStart == -1 && shapeArr[r][c])
	                shapeStart = r;
	        } // end for
	    } // end for
	    
	    for(int r = shapeArr.length - 1; r >= 0; r --) {
            for(int c = 0; c < shapeArr[0].length; c ++ ) {
                if(shapeEnd == -1 && shapeArr[r][c])
                    shapeEnd = r;
            } // end for
        } // end for
	    
	    dimensions[0] = shapeEnd - shapeStart + 1;
	    shapeStart = -1;
	    shapeEnd = -1;
	    
	    // determining the width of the given shape
	    for(int c = 0; c < shapeArr[0].length; c ++) {
            for(int r = 0; r < shapeArr.length; r ++ ) {
                if(shapeStart == -1 && shapeArr[r][c])
                    shapeStart = c;
            } // end for
        } // end for
	    
	    for(int c = shapeArr[0].length - 1; c >= 0; c --) {
            for(int r = 0; r < shapeArr.length; r ++ ) {
                if(shapeEnd == -1 && shapeArr[r][c])
                    shapeEnd = c;
            } // end for
        } // end for
	    
	    dimensions[1] = shapeEnd - shapeStart + 1;
	    
	    return dimensions;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	public int getWidth()
	{
		return this.width;
	}
	public void setShape(char shape, int orientation)
	{
	    shape = Character.toUpperCase(shape);
	    
        switch(shape) {
		
		case 'O':
			arraycopy2D(O[orientation], 0, this.shape, 0, O[orientation].length);
	        this.currentShape=shape;
	        break;
		case 'T':
			arraycopy2D(T[orientation], 0, this.shape, 0, T[orientation].length);
			this.currentShape=shape;
			break;
		case 'S':
			arraycopy2D(S[orientation], 0, this.shape, 0, T[orientation].length);
			this.currentShape=shape;
			break;
		case 'I':
			arraycopy2D(I[orientation], 0, this.shape, 0, I[orientation].length);
			this.currentShape=shape;
			break;
		case 'l':
			arraycopy2D(L[orientation], 0, this.shape, 0, L[orientation].length);
			this.currentShape=shape;
			break;
		case 'Z':
			arraycopy2D(Z[orientation], 0, this.shape, 0, Z[orientation].length);
			this.currentShape=shape;
			break;
		case 'J':
			arraycopy2D(J[orientation], 0, this.shape, 0, J[orientation].length);
			this.currentShape=shape;
			break;
		default:
			break;
		}
        
        int[] dimensions = getDimensions(shape, orientation);
        this.height = dimensions[0];
        this.width = dimensions[1];
	
	}
	public void setShape(int orientation)
	{
	 	setShape(this.currentShape,orientation);
	}
	public boolean[][] getShape()
	{
		return shape;
	}
}