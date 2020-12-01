/**Author Helen Wubneh
* Due Date 12/2/2020
* Class CSCI-3033-001
*/
import java.io.*;
import java.io.IOException;

public class Score {

	private int score;
	private int highScore;
        //a Score constructor that initializes score and highScore
	public Score()
	{
		this.score=0;
		try {
	         BufferedReader in = new BufferedReader(new FileReader("highScore.txt"));
	         String str;

	         while ((str = in.readLine()) != null) {
	            this.highScore=Integer.parseInt(str);
	         }
	         
	         in.close();
	         
	      } catch (IOException e) {
	          // no high score, so we will create the high score file
	          highScore = -1;
	          saveHighScore();
	      }
	}
    //increaseScore method that increases score by given amount
    public void increaseScore(int amount)
    {
    	this.score+=amount;
    }
    //SaveGighScore method that same the score into highScore if it is greater than it
    public void saveHighScore()
    {
    	if(this.score>this.highScore)
    	{
    		this.highScore=this.score;

    		 try {
    	         BufferedWriter out = new BufferedWriter(new FileWriter("highScore.txt"));
    	         out.write("" + this.highScore);
    	         out.close();
    	      }
    	      catch (IOException e) {
    	          System.out.println("Error: failed to write high score to file.");
    	      }
    	}
    }
    //getter method that returns score
    public int getScore()
    {
    	return score;
    }
    //getter method that returns highScore
    public int getHighScore()
    {
        return highScore;
    }
}
