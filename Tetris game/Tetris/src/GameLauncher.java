/**
 * @author Laura Guo
 * 15 November 2020
 * Description: This class is the main starting point for the Tetris game. It brings up the main menu, handles options, and handles
 *      the game over scene.
 */

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class GameLauncher extends Application {
    public static final double ITEM_SPACING = 10;
    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT = (int) (WINDOW_WIDTH * 0.7);
    
    @Override
    public void start(Stage stage) {
        Score score = new Score();
        Sound sound = new Sound(0.25);
        Game game = new Game();
        
        // PROGRAM MAIN MENU
        // play music
        
        // display game name
        Label gameNameLabel = new Label("TETRIS");
        
        // display high score
        Label highScoreLabel = new Label(String.format("High Score\t%s", score.getHighScore()));
        
        // play game button
        Button playButton = new Button("Play Game");
        
        // options button
        Button optionsButton = new Button("Options");
        
        VBox titlePane = new VBox(ITEM_SPACING, gameNameLabel, highScoreLabel);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox optionsPane = new VBox(ITEM_SPACING, playButton, optionsButton);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox mainMenuPane = new VBox(titlePane, optionsPane);
        Scene mainMenuScene = new Scene(mainMenuPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(mainMenuScene);
        
        // OPTIONS SCENE
        // sound buttons/label
        Label volumeLabel = new Label("Volume\t");
        Button decreaseVolumeButton = new Button("-");
        Label currentVolumeLabel = new Label(((int) sound.getVolume() * 100) + "");
        Button increaseVolumeButton = new Button("+");
        
        // return to main menu
        Button returnMainMenuButton = new Button("Back to Main Menu");
        
        HBox volumePane = new HBox(ITEM_SPACING, volumeLabel, decreaseVolumeButton, currentVolumeLabel, increaseVolumeButton);
        VBox optionMenuPane = new VBox(ITEM_SPACING, volumePane, returnMainMenuButton);
        Scene optionsScene = new Scene(optionMenuPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // GAME OVER SCENE
        // game over label
        Label gameOverLabel = new Label("GAME OVER");
        
        // player score
        Label scoreLabel = new Label(String.format("Your Score\t%d", game.getScore()));
        
        // special text for new high score
        Label newHighScoreLabel = new Label("");
        
        if(game.getScore() > score.getHighScore()) {
            newHighScoreLabel.setText("CONGRATULATIONS! New high score!");
        } // end if
        
        // play again button
        Button playAgainButton = new Button("Play Again");
        
        VBox endTextPane = new VBox(ITEM_SPACING, gameOverLabel, scoreLabel);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox endOptionsPane = new VBox(ITEM_SPACING, playAgainButton, returnMainMenuButton);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox endPane = new VBox(endTextPane, endOptionsPane);
        Scene endScene = new Scene(endOptionsPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        
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
        });
        
        increaseVolumeButton.setOnAction(e -> {
            sound.increaseVolume();
        });
        
        returnMainMenuButton.setOnAction(e -> {
            stage.setScene(mainMenuScene);
        });
        
        playAgainButton.setOnAction(e -> {
            game = new Game();
            game.playGame(stage, endScene);
        });
        
        stage.show();
    } // end start
    
    public static void main(String args[]) {
        Application.launch(args);
    } // end main
} // end GameLauncher
