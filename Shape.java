
public class Shape {
	private int height=2;
	private int width=4;
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

          final static boolean [][][] l = {{{false, false, true},
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

                   
	public Shape()
	{
		
		this.shape= new boolean[2][4];
		
	}
	public Shape(char shape, int orentiation)
	{
		setShape(shape, orentiation);
	}
	private void arraycopy2D(boolean[][] src, int srcPos, boolean[][]  dst,int destPos, int length)
	{
		
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], srcPos, dst[i],destPos, src[i].length);
	    }
	}
	
	public int getHeight()
	{
		return this.height;
	}
	public int getWidth()
	{
		return this.width;
	}
	private	void setShape(char shape, int orentiation)
	{
        switch(shape) {
		
		case 'O':
			arraycopy2D(O[orentiation], 0, this.shape, 0, O[orentiation].length);
	        this.height=O[orentiation].length;
	        this.width=O[orentiation][0].length;
	        this.currentShape=shape;
	        break;
		case 'T':
			arraycopy2D(T[orentiation], 0, this.shape, 0, T[orentiation].length);
			this.height=T[orentiation].length;
			this.width=T[orentiation][0].length;
			this.currentShape=shape;
			break;
		case 'S':
			arraycopy2D(S[orentiation], 0, this.shape, 0, T[orentiation].length);
			this.height=S[orentiation].length;
			this.width=S[orentiation][0].length;
			this.currentShape=shape;
			break;
		case 'I':
			arraycopy2D(I[orentiation], 0, this.shape, 0, T[orentiation].length);
			this.height=I[orentiation].length;
			this.width=I[orentiation][0].length;
			this.currentShape=shape;
			break;
		case 'l':
			arraycopy2D(l[orentiation], 0, this.shape, 0, l[orentiation].length);
			this.height=l[orentiation].length;
			this.width=l[orentiation][0].length;
			this.currentShape=shape;
			break;
		case 'Z':
			arraycopy2D(Z[orentiation], 0, this.shape, 0, Z[orentiation].length);
			this.height=Z[orentiation].length;
			this.width=Z[orentiation][0].length;
			this.currentShape=shape;
			break;
		case 'J':
			arraycopy2D(J[orentiation], 0, this.shape, 0, J[orentiation].length);
			this.height=J[orentiation].length;
			this.width=J[orentiation][0].length;
			this.currentShape=shape;
			break;
		default:
			break;
		}
	
	}
	public void setShape(int orentiation)
	{
	 	setShape(this.currentShape,orentiation);
	}
	public boolean[][] getShape()
	{
		return shape;
	}
}