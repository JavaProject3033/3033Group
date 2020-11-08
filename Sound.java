
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class Sound {
    private String filename;
    private Player player;
    private double volumeLevel;

    public Sound(String filename) {
        this.filename = "test.mp3";
        this.volumeLevel=0.5;
        
    }
    public void setVolume(double newVolumeLevel)
    {
 	   if(newVolumeLevel>=0.5 && newVolumeLevel<=1.0)
 		   volumeLevel=newVolumeLevel;
    }
    
    public void increaseVolume()
    {
    	if(volumeLevel<1.0)
    		volumeLevel++;
    }
    public void decreaseVolume()
    {
    	if(volumeLevel>0.5)
    		volumeLevel--;
    }
    
    public void playSong() {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();

    }
    public void close() { if (player != null) player.close(); }
    
    public boolean complete(){
    	return player.isComplete();
    }
    
}
