/**Created by Helen Wubneh
* Due Date 12/2/2020
* Class CSCI-3033-001
*/
import javafx.scene.media.Media; 
import javafx.scene.media.MediaPlayer; 
import java.io.*;

//This class plays music and controls volume 

public class Sound {
    private double volume;
    private static String music = "tetrisSong.mp3";
    final double VOLUME_CHANGE_AMOUNT=0.1;
    Media song; 
    MediaPlayer player;
    //A default constructor that initalizes volume
    public Sound() {
        this(0.20);
    }
    //A Sound constructor that initializes volume, music,song, and Player
    public Sound(double volume)
    {
        File songFile = new File(music);
    	this.volume=volume;
    	song=new Media(songFile.toURI().toString());
    	player=new MediaPlayer(song);
    	player.setCycleCount(MediaPlayer.INDEFINITE);
    	player.setVolume(volume);
    } 
    //A increaseVolume method that increase a volume by specific amount
    public void increaseVolume()
    {
    	if(volume<1)
    		this.volume+=VOLUME_CHANGE_AMOUNT;
    	
    	if(volume > 1) {
    	    this.volume = 1;
    	}
    	
    	player.setVolume(volume);
    }
    //A decreaseVolume method that decrease a volume by specific amount
    public void decreaseVolume()
    {
    	if(volume>0)
    		volume-=VOLUME_CHANGE_AMOUNT;
    	
    	if(volume < 0) {
            this.volume = 0;
        }
    	
    	player.setVolume(volume);
    }
    //A playSong method play the background music 
    public void playSong() 
    {
        player.play();
    }
   //getter method that returns volume
    public double getVolume() {
        return volume;
    }
    //setter method to set the volume
    public void setVolume(double v) {
        volume = v;
    }
    
}
