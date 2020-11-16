
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javafx.scene.media.Media; 
import javafx.scene.media.MediaPlayer; 

public class Sound {
    private  double volume;
    private String music="test.mp3";
    final int VOLUME_CHANGE_AMOUNT=0.1;
    Media song; 
    MediaPlayer player;
    
    public Sound() {
        this.volume=0.5;
         song=new Media();
         player=new MediaPlayer;
    }
    public Sound(double volume)
    {
    	this.volume=volume;
    	song=new Media();
    	player=new MediaPlayer();
    }
   
    public void increaseVolume()
    {
    	if(volumeLevel<1)
    		this.volume+=VOLUME_CHANGE_AMOUNT;
    }
    public void decreaseVolume()
    {
    	if(volumeLevel>0)
    		volume-=VOLUME_CHANGE_AMOUNT;
    }
    
    public void playSong() 
    {
            media = new Media(new File(music).toURI().toString())); 
            player = new MediaPlayer(media);
            mediaPlayer.play();
    }
   
}
