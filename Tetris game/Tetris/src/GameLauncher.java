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
    @Override
    public void start(Stage stage) {
        double width = 700;
        double height = width * 0.7;
        double itemSpacing = 10;
        
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
        
        VBox titlePane = new VBox(itemSpacing, gameNameLabel, highScoreLabel);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox optionsPane = new VBox(itemSpacing, playButton, optionsButton);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox mainMenuPane = new VBox(titlePane, optionsPane);
        Scene mainMenuScene = new Scene(mainMenuPane, width, height);
        stage.setScene(mainMenuScene);
        
        // OPTIONS SCENE
        // sound buttons/label
        Label volumeLabel = new Label("Volume\t");
        Button decreaseVolumeButton = new Button("-");
        Label currentVolumeLabel = new Label(((int) sound.getVolume() * 100) + "");
        Button increaseVolumeButton = new Button("+");
        
        // return to main menu
        Button returnMainMenuButton = new Button("Back to Main Menu");
        
        HBox volumePane = new HBox(itemSpacing, volumeLabel, decreaseVolumeButton, currentVolumeLabel, increaseVolumeButton);
        VBox optionMenuPane = new VBox(itemSpacing, volumePane, returnMainMenuButton);
        Scene optionsScene = new Scene(optionMenuPane, width, height);
        
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
        
        VBox endTextPane = new VBox(itemSpacing, gameOverLabel, scoreLabel);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox endOptionsPane = new VBox(itemSpacing, playAgainButton, returnMainMenuButton);
        titlePane.setAlignment(Pos.CENTER);
        
        VBox endPane = new VBox(endTextPane, endOptionsPane);
        Scene endScene = new Scene(endOptionsPane, width, height);
        
        // EVENT HANDLERS
        playButton.setOnAction(e -> {
            // go to the game scene
            stage.setScene(game.playGame());
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
            stage.setScene(game.playGame());
        });
        
        stage.show();
    } // end start
    
    public static void main(String args[]) {
        Application.launch(args);
    } // end main
} // end GameLauncher
