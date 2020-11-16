import java.io.*;
import java.io.IOException;

public class Score {

	private int score;
	private int highScore;
	
	public Score()
	{
		this.score=0;
		
		try {
	         BufferedReader in = new BufferedReader(new FileReader("highScore.txt"));
	         String str;
	         
	         while ((str = in.readLine()) != null) {
	            this.highScore=Integer.parseInt(str);
	            in.close();
	         }
	      } catch (IOException e) {
	      }
	}
		
    public void increaseScore(int amount)
    {
    	this.score+=amount;
    }
    
    public void saveHighScore()
    {
    	if(this.score>this.highScore)
    	{
    		this.highScore=this.score;
    		
    		 try {
    	         BufferedWriter out = new BufferedWriter(new FileWriter("outfilename"));
    	         out.write(this.highScore);
    	         out.close();
    	      }
    	      catch (IOException e) {
    	      }
    	}
    }
    public int getScore()
    {
    	return score;
    }
    /*
     if needed
    public void setScore(int score)
    {
    	this.score=score;
    }
    */
    

}
