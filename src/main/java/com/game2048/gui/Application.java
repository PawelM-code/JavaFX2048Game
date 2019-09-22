package com.game2048.gui;

import com.game2048.logic.Board;
import com.game2048.logic.GameMove;
import com.game2048.logic.Move;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
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
                if(Board.getSizeOfBoard() == 3){
                    Rectangle rectangle = new Rectangle(160, 160);
                    setRectangleFill(rectangle, gridNumber);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(1);
                    grid.add(rectangle, col, row);
                    grid.add(fieldNumber, col, row);
                }else {
                    Rectangle rectangle = new Rectangle(120, 120);
                    setRectangleFill(rectangle, gridNumber);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(1);
                    grid.add(rectangle, col, row);
                    grid.add(fieldNumber, col, row);
                }

            }
    }

    private void setRectangleFill(Rectangle rectangle, int gridNumber) {
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
    }

    @Override
    public void newGame(Stage stage) {
        Board board = new Board();
        GameMove gameMove = new GameMove();
        BorderPane borderPane = new BorderPane();
        borderPane.setMaxSize(700,700);
        borderPane.setMinSize(600,600);
//        borderPane.setCenter(grid);

        StackPane stackPaneTop = new StackPane();
        Text titleGame = new Text("2048");
        titleGame.setStyle("-fx-font-size: 40");
        BorderPane.setAlignment(titleGame, Pos.CENTER);
        stackPaneTop.getChildren().add(titleGame);

        Button newGame = new Button();
        newGame.setText("New Game");
        newGame.setOnAction(e -> restart(stage));
        newGame.getStylesheets().add("/com/game2048/gui/style.css");

        Button boardSize = new Button();
        boardSize.setText("3x3 || 4x4");
        boardSize.setOnAction(e -> {if(Board.getSizeOfBoard()==3){
                                        Board.setSizeOfBoard(4);
                                    }else {
                                        Board.setSizeOfBoard(3);
                                    }
                                    restart(stage);});
        boardSize.getStylesheets().add("/com/game2048/gui/style.css");

        GridPane setBottom = new GridPane();
        setBottom.getStyleClass().add("/com/game2048/gui/style.css");

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        setBottom.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        setBottom.getColumnConstraints().add(column2);
        GridPane.setColumnIndex(setBottom, 2);
        GridPane.setRowIndex(setBottom, 1);
        setBottom.getChildren().addAll(boardSize, newGame);
        GridPane.setConstraints(newGame, 1, 0);
        GridPane.setConstraints(boardSize, 0, 0);
        GridPane.setHalignment(newGame, HPos.RIGHT);
        GridPane.setHalignment(boardSize, HPos.LEFT);
        GridPane.setMargin(newGame, new Insets(10, 10, 10, 10));
        GridPane.setMargin(boardSize, new Insets(10, 10, 10, 10));

        grid = new GridPane();
        GridPane.setColumnIndex(grid, Board.getSizeOfBoard());
        GridPane.setRowIndex(grid, Board.getSizeOfBoard());
        GridPane.setHalignment(grid,HPos.CENTER);
        grid.setMaxSize(480, 480);

        StackPane stackPaneCenter = new StackPane();
        StackPane.setAlignment(grid,Pos.CENTER);
        stackPaneCenter.setMinWidth(380);
        stackPaneCenter.setMinHeight(380);
        stackPaneCenter.getChildren().addAll(grid);

        borderPane.setCenter(stackPaneCenter);
        borderPane.setTop(titleGame);
        borderPane.setBottom(setBottom);

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
