package com.game2048.gui;

import com.game2048.logic.Board;
import com.game2048.logic.GameMove;
import com.game2048.logic.Move;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Application extends javafx.application.Application implements NewGame {
    private GridPane grid;

    public void displayOnGrid(Board board) {
        grid.getChildren().clear();
        for (int row = 0; row < Board.getSizeOfBoard(); row++)
            for (int col = 0; col < Board.getSizeOfBoard(); col++) {
                int gridNumber = board.getBoardFieldNumber(row, col);
                Label fieldNumber = new Label(Integer.toString(gridNumber));
                if(gridNumber == 0){
                    fieldNumber.setTextFill(Color.TRANSPARENT);
                }
                fieldNumber.setStyle("-fx-font-size: 40");
                GridPane.setHalignment(fieldNumber, HPos.CENTER);
                Rectangle rectangle = new Rectangle(160, 160);
                if(gridNumber == 0){
                    rectangle.setFill(Color.WHITE);
                }
                else if(gridNumber == 2){
                    rectangle.setFill(Color.LIGHTGRAY);
                }
                else if(gridNumber == 4){
                    rectangle.setFill(Color.GRAY);
                }
                else if(gridNumber == 8){
                    rectangle.setFill(Color.DARKGRAY);
                }
                else if(gridNumber == 16){
                    rectangle.setFill(Color.LIGHTBLUE);
                }
                else if(gridNumber == 32){
                    rectangle.setFill(Color.BLUE);
                }
                else if(gridNumber == 64){
                    rectangle.setFill(Color.DARKBLUE);
                }
                else if(gridNumber == 128){
                    rectangle.setFill(Color.YELLOW);
                }
                else if(gridNumber == 256){
                    rectangle.setFill(Color.RED);
                }
                else if(gridNumber == 512){
                    rectangle.setFill(Color.GREEN);
                }
                else if(gridNumber == 1024){
                    rectangle.setFill(Color.BEIGE);
                }
                else if(gridNumber == 2048){
                    rectangle.setFill(Color.WHITE);
                }
                else if(gridNumber > 2048){
                    rectangle.setFill(Color.LIGHTGRAY);
                }
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(1);
                grid.add(rectangle, col, row);
                grid.add(fieldNumber, col, row);
            }
    }

    @Override
    public void newGame(Stage stage) {
        Board board = new Board();
        GameMove gameMove = new GameMove();
        BorderPane borderPane = new BorderPane();

        StackPane stackPaneTop = new StackPane();
        Text titleGame = new Text("2048");
        titleGame.setStyle("-fx-font-size: 40");
        BorderPane.setAlignment(titleGame, Pos.CENTER);
        stackPaneTop.getChildren().add(titleGame);

        grid = new GridPane();
        GridPane.setColumnIndex(grid, Board.getSizeOfBoard());
        GridPane.setRowIndex(grid, Board.getSizeOfBoard());
        grid.setMaxSize(480, 480);

        StackPane stackPaneCenter = new StackPane();
        stackPaneCenter.setMaxWidth(560);
        stackPaneCenter.setMaxHeight(560);
        stackPaneCenter.getChildren().addAll(grid);

        borderPane.setCenter(stackPaneCenter);
        borderPane.setTop(titleGame);

        displayOnGrid(board);

        Scene scene = new Scene(borderPane);
        scene.setOnKeyPressed(e -> {
            if (!board.isEndOfGame()) {
                if (e.getCode() == KeyCode.UP) {
                    gameMove.move(Move.UP, board);
                } else if (e.getCode() == KeyCode.DOWN) {
                    gameMove.move(Move.DOWN, board);
                } else if (e.getCode() == KeyCode.LEFT) {
                    gameMove.move(Move.LEFT, board);
                } else if (e.getCode() == KeyCode.RIGHT) {
                    gameMove.move(Move.RIGHT, board);
                }
                displayOnGrid(board);
            }
        });
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.setMaxHeight(700);
        stage.setMaxWidth(700);
        stage.setMinHeight(640);
        stage.setMinWidth(640);
        stage.show();
    }

    private void restart(Stage stage) {
        newGame(stage);
    }

    @Override
    public void start(Stage primaryStage) {
        newGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
