package com.game2048.gui;

import com.game2048.logic.Board;
import com.game2048.logic.GameMove;
import com.game2048.logic.Move;
import com.game2048.logic.Save;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.game2048.logic.Board.*;

public class Application extends javafx.application.Application implements NewGame {
    private GridPane grid;
    private Save save = new Save();


    public void displayOnGrid(Board board) {
        grid.getChildren().clear();
        for (int row = 0; row < getSizeOfBoard(); row++)
            for (int col = 0; col < getSizeOfBoard(); col++) {
                int gridNumber = board.getBoardFieldNumber(row, col);
                Label fieldNumber = new Label(Integer.toString(gridNumber));
                if (gridNumber == 0) {
                    fieldNumber.setTextFill(Color.TRANSPARENT);
                }
                fieldNumber.setStyle("-fx-font-size: 40");
                GridPane.setHalignment(fieldNumber, HPos.CENTER);
                if (getSizeOfBoard() == 3) {
                    Rectangle rectangle = new Rectangle(160, 160);
                    setRectangleGrid(row, col, gridNumber, fieldNumber, rectangle);
                } else {
                    Rectangle rectangle = new Rectangle(120, 120);
                    setRectangleGrid(row, col, gridNumber, fieldNumber, rectangle);
                }

            }
    }

    private void setRectangleGrid(int row, int col, int gridNumber, Label fieldNumber, Rectangle rectangle) {
        setRectangleFill(rectangle, gridNumber);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setStrokeWidth(3);
        rectangle.setArcWidth(30.0);
        rectangle.setArcHeight(30.0);
        grid.add(rectangle, col, row);
        grid.add(fieldNumber, col, row);
    }

    private void setRectangleFill(Rectangle rectangle, int gridNumber) {
        if (gridNumber == 0) {
            rectangle.setFill(Color.web("#EBF5FB"));
        } else if (gridNumber == 2) {
            rectangle.setFill(Color.web("#D4E6F1"));
        } else if (gridNumber == 4) {
            rectangle.setFill(Color.web("#A9CCE3"));
        } else if (gridNumber == 8) {
            rectangle.setFill(Color.web("#7FB3D5"));
        } else if (gridNumber == 16) {
            rectangle.setFill(Color.web("#5499C7"));
        } else if (gridNumber == 32) {
            rectangle.setFill(Color.web("#2980B9"));
        } else if (gridNumber == 64) {
            rectangle.setFill(Color.web("#2471A3"));
        } else if (gridNumber == 128) {
            rectangle.setFill(Color.web("#1F618D"));
        } else if (gridNumber == 256) {
            rectangle.setFill(Color.web("#1A5276"));
        } else if (gridNumber == 512) {
            rectangle.setFill(Color.web("#154360"));
        } else if (gridNumber == 1024) {
            rectangle.setFill(Color.web("#0f2e42"));
        } else if (gridNumber == 2048) {
            rectangle.setFill(Color.web("#F1C40F"));
        } else if (gridNumber > 2048) {
            rectangle.setFill(Color.RED);
        }
    }

    @Override
    public void newGame(Stage stage) {
        Board board = new Board();
        GameMove gameMove = new GameMove();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: white");
        borderPane.setMaxSize(700, 700);
        borderPane.setMinSize(600, 600);

        StackPane stackPaneTop = new StackPane();
        Text titleGame = new Text("2048");
        titleGame.setStyle("-fx-font-size: 40");
        BorderPane.setAlignment(titleGame, Pos.CENTER);
        stackPaneTop.getChildren().add(titleGame);
        titleGame.getStyleClass().add("/com/game2048/gui/style.css");

        Button newGame = new Button();
        newGame.setText("New Game");
        newGame.setOnAction(e -> restart(stage));
        newGame.getStylesheets().add("/com/game2048/gui/style.css");

        Button boardSize = new Button();
        boardSize.setText("3x3 || 4x4");
        boardSize.setOnAction(e -> {
            restart(stage);
            if (getSizeOfBoard() == 3) {
                setSizeOfBoard(4);
            } else {
                setSizeOfBoard(3);
            }
            restart(stage);
        });
        boardSize.getStylesheets().add("/com/game2048/gui/style.css");

        Button loadSave = new Button();
        loadSave.setText("Load save");
        loadSave.setOnAction(e -> getSave(board));
        loadSave.getStylesheets().add("/com/game2048/gui/style.css");

        GridPane setBottom = new GridPane();
        setBottom.getStyleClass().add("/com/game2048/gui/style.css");

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(15);
        setBottom.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(15);
        setBottom.getColumnConstraints().add(column2);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(70);
        setBottom.getColumnConstraints().add(column3);
        GridPane.setColumnIndex(setBottom, 3);
        GridPane.setRowIndex(setBottom, 1);
        setBottom.getChildren().addAll(boardSize, loadSave, newGame);
        GridPane.setConstraints(boardSize, 0, 0);
        GridPane.setConstraints(loadSave, 1, 0);
        GridPane.setConstraints(newGame, 2, 0);
        GridPane.setHalignment(newGame, HPos.RIGHT);
        GridPane.setHalignment(boardSize, HPos.LEFT);
        GridPane.setHalignment(loadSave, HPos.LEFT);
        GridPane.setMargin(newGame, new Insets(10, 10, 10, 10));
        GridPane.setMargin(boardSize, new Insets(10, 10, 10, 10));
        GridPane.setMargin(loadSave, new Insets(10, 10, 10, 10));

        grid = new GridPane();
        grid.getStylesheets().add("/com/game2048/gui/style.css");
        GridPane.setColumnIndex(grid, getSizeOfBoard());
        GridPane.setRowIndex(grid, getSizeOfBoard());
        GridPane.setHalignment(grid, HPos.CENTER);
        grid.setMaxSize(480, 480);

        StackPane stackPaneCenter = new StackPane();
        StackPane.setAlignment(grid, Pos.CENTER);
        stackPaneCenter.setMinWidth(380);
        stackPaneCenter.setMinHeight(380);
        stackPaneCenter.getChildren().addAll(grid);

        borderPane.setCenter(stackPaneCenter);
        borderPane.setTop(titleGame);
        borderPane.setBottom(setBottom);
        displayOnGrid(board);


        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("/com/game2048/gui/style.css");
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
        stage.setOnCloseRequest(event -> save.writeObjectToFile(board.getBoard()));
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
    }


    private void restart(Stage stage) {
        newGame(stage);
    }

    private void getSave(Board board) {
        try {
            int[][] saveBoard = save.readObjectFromFile();
            if (saveBoard.length == 3 && getSizeOfBoard() == 3) {
                board.setSavedBoard(saveBoard);
                displayOnGrid(board);
            } else if (saveBoard.length == 4 && getSizeOfBoard() == 4) {
                board.setSavedBoard(saveBoard);
                displayOnGrid(board);
            }

        } catch (Exception ex) {
            System.out.println("File not found");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        newGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
