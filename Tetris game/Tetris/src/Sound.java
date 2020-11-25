
import javafx.scene.media.Media; 
import javafx.scene.media.MediaPlayer; 
import java.io.*;

public class Sound {
    private double volume;
    private static String music = "tetrisSong.mp3";
    final double VOLUME_CHANGE_AMOUNT=0.1;
    Media song; 
    MediaPlayer player;
    
    public Sound() {
        this(0.20);
    }
    public Sound(double volume)
    {
        File songFile = new File(music);
    	this.volume=volume;
    	song=new Media(songFile.toURI().toString());
    	player=new MediaPlayer(song);
    }
   
    public void increaseVolume()
    {
    	if(volume<1)
    		this.volume+=VOLUME_CHANGE_AMOUNT;
    	
    	if(volume > 1) {
    	    this.volume = 1;
    	}
    }
    public void decreaseVolume()
    {
    	if(volume>0)
    		volume-=VOLUME_CHANGE_AMOUNT;
    	
    	if(volume < 0) {
            this.volume = 0;
        }
    }
    
    public void playSong() 
    {
        player.play();
    }
   
    public double getVolume() {
        return volume;
    }
    
    public void setVolume(double v) {
        volume = v;
    }
    
}
