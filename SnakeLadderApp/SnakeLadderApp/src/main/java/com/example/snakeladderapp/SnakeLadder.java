package com.example.snakeladderapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize =40, width=10, height=10;
    public static final int buttonLine = height*tileSize + 50, infoLine = buttonLine - 30;
    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;
    private boolean gameStarted = false, playerOneTurn = false,playerTwoTurn = false;

    private Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize + 100 );

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image(new FileInputStream("/Users/KI20387400/IdeaProjects/SnakeLadderApp/src/main/sla.jpeg"));
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //Buttons
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);

        //Info Display
         Label playerOneLabel = new Label("");
         Label playerTwoLabel = new Label("");
         Label diceLabel = new Label("Start the GAME !!");

         playerOneLabel.setTranslateY(infoLine);
         playerOneLabel.setTranslateX(20);
         playerTwoLabel.setTranslateY(infoLine);
         playerTwoLabel.setTranslateX(300);
         diceLabel.setTranslateY(infoLine);
         diceLabel.setTranslateX(160);

         playerOne = new Player(tileSize, Color.BLACK,"Mohan");
         playerTwo = new Player(tileSize-5, Color.WHITE,"Rohan");

         //Player Action
         playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 if(gameStarted){
                     if(playerOneTurn){
                         int diceValue = dice.getRolledDiceValue();
                         diceLabel.setText("Dice Value: " + diceValue);
                         playerOne.movePlayer(diceValue);

                         //Winning Condition
                         if(playerOne.isWinner()){
                             diceLabel.setText("Winner is " + playerTwo.getName());
                             playerOneTurn = false;
                             playerOneButton.setDisable(true);
                             playerOneLabel.setText("");

                             playerTwoTurn = false;
                             playerTwoButton.setDisable(true);
                             playerTwoLabel.setText("");

                             startButton.setDisable(false);
                             startButton.setText("Play Again");
                             gameStarted=false;
                         }else {

                             playerOneTurn = false;
                             playerOneButton.setDisable(true);
                             playerOneLabel.setText("");

                             playerTwoTurn = true;
                             playerTwoButton.setDisable(false);
                             playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                         }

                     }
                 }


             }
         });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value: " + diceValue);
                        playerTwo.movePlayer(diceValue);

                        //Winning Condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Play Again");
                            gameStarted=false;
                        }else{
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }

                    }
                }


            }
        });

         startButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 gameStarted = true;
                 diceLabel.setText("Game Started");
                 startButton.setDisable(true);

                 playerOneTurn = true;
                 playerOneLabel.setText("Your Turn " + playerOne.getName());
                 playerOneButton.setDisable(false);
                 playerOne.startingPosition();

                 playerTwoTurn = false;
                 playerTwoLabel.setText("");
                 playerTwoButton.setDisable(true);
                 playerTwo.startingPosition();
             }
         });

        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,diceLabel,playerOne.getCoin(),playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Dragon & Ladder !!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}