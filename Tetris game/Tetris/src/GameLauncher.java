/**
 * @author Laura Guo
 * 15 November 2020
 * Description: This class is the main starting point for the Tetris game. It brings up the main menu, handles options, and handles
 *      the game over scene.
 */

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GameLauncher extends Application {
    public static final double ITEM_SPACING = 10;
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = (int) (WINDOW_WIDTH * 0.7);
    public static Game game;
    
    private static Label newHighScoreLabel; // Game class needs access to these
    private static Label scoreLabel;
    private static Score score;
    
    @Override
    public void start(Stage stage) {
        score = new Score();
        Sound sound = new Sound(0.05);
        game = new Game();
        
        // PROGRAM MAIN MENU
        // play music
        sound.playSong();
        
        // display game name
        Label gameNameLabel = new Label("TETRIS");
        gameNameLabel.setFont(new Font(72));
        
        // display high score
        Label highScoreLabel = new Label(String.format("High Score\t%s", score.getHighScore()));
        
        // play game button
        Button playButton = new Button("Play Game");
        
        // options button
        Button optionsButton = new Button("Options");
        
        // credit for music
        int creditFontSize = 12;

        Label musicCreditLabel = new Label("Music created by Bogozi");
        Label musicLicenseLabel = new Label("Licensed under the Creative Commons Attribution-Share Alike 3.0 Unported license.");
        musicCreditLabel.setFont(new Font(creditFontSize));
        musicLicenseLabel.setFont(new Font(creditFontSize));
        
        VBox titlePane = new VBox(ITEM_SPACING, gameNameLabel, highScoreLabel);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPadding(new Insets(50));
        
        VBox optionsPane = new VBox(ITEM_SPACING, playButton, optionsButton);
        optionsPane.setAlignment(Pos.CENTER);
        optionsPane.setPadding(new Insets(30));
        
        VBox musicAttributionPane = new VBox(0, musicCreditLabel, musicLicenseLabel);
        musicAttributionPane.setAlignment(Pos.BOTTOM_CENTER);
        musicAttributionPane.setPadding(new Insets(100, 5, 5, 5));
        
        VBox mainMenuPane = new VBox(titlePane, optionsPane, musicAttributionPane);
        mainMenuPane.setAlignment(Pos.CENTER);
        Scene mainMenuScene = new Scene(mainMenuPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(mainMenuScene);
        
        // OPTIONS SCENE
        // sound buttons/label
        Label volumeLabel = new Label("Volume\t");
        Button decreaseVolumeButton = new Button("-");
        Label currentVolumeLabel = new Label((Math.round(sound.getVolume() * 100) + ""));
        Button increaseVolumeButton = new Button("+");
        
        HBox volumePane = new HBox(ITEM_SPACING, volumeLabel, decreaseVolumeButton, currentVolumeLabel, increaseVolumeButton);
        volumePane.setAlignment(Pos.CENTER);
        
        // return to main menu
        Button returnMainMenuButton = new Button("Back to Main Menu");
        returnMainMenuButton.setAlignment(Pos.BOTTOM_CENTER);  
        
        VBox optionMenuPane = new VBox(ITEM_SPACING * 5, volumePane, returnMainMenuButton);
        optionMenuPane.setAlignment(Pos.CENTER);
        
        Scene optionsScene = new Scene(optionMenuPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // GAME OVER SCENE
        // game over label
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(new Font(60));
        
        // player score
        scoreLabel = new Label(String.format("Your Score\t%d", game.getScore()));

        // special text for new high score
        newHighScoreLabel = new Label("");
        
        // play again button
        Button playAgainButton = new Button("Main Menu");
        
        VBox endTextPane = new VBox(ITEM_SPACING, gameOverLabel, scoreLabel, newHighScoreLabel);
        endTextPane.setAlignment(Pos.CENTER);
        endTextPane.setPadding(new Insets(50));
        
        VBox endOptionsPane = new VBox(ITEM_SPACING, playAgainButton);
        endOptionsPane.setAlignment(Pos.BOTTOM_CENTER);
        endOptionsPane.setPadding(new Insets(30));
        
        VBox endPane = new VBox(ITEM_SPACING, endTextPane, endOptionsPane);
        endPane.setAlignment(Pos.CENTER);
        
        Scene endScene = new Scene(endPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // EVENT HANDLERS
        playButton.setOnAction(e -> {
            // go to the game scene
            game.playGame(stage, endScene);
        });
        
        optionsButton.setOnAction(e -> {
            // go to the options scene
            stage.setScene(optionsScene);
        });
        
        decreaseVolumeButton.setOnAction(e -> {
            sound.decreaseVolume();
            currentVolumeLabel.setText(Math.round(sound.getVolume() * 100) + "");
        });
        
        increaseVolumeButton.setOnAction(e -> {
            sound.increaseVolume();
            currentVolumeLabel.setText(Math.round(sound.getVolume() * 100) + "");
        });
        
        returnMainMenuButton.setOnAction(e -> {
            stage.setScene(mainMenuScene);
        });
        
        playAgainButton.setOnAction(e -> {
            stage.setScene(mainMenuScene); 
            
            if(game.getExitGame()) {
                game.blockFallingTimeline.stop();
            } // end if
            
            game = new Game();
            highScoreLabel.setText(String.format("High Score\t%s", score.getHighScore()));
        });
        
        stage.show();
    } // end start
    
    public static Label getHighScoreLabel() {
        return newHighScoreLabel;
    } // end getHighSchoreLabel
    
    public static Label getScoreLabel() {
        return scoreLabel;
    } // end getScoreLabel
    
    public static Score getScore() {
        return score;
    } // end getScore
    
    public static void main(String args[]) {
        Application.launch(args);
    } // end main
} // end GameLauncher
