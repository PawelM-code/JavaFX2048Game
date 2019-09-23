package com.game2048.gui;

import com.game2048.logic.Board;
import javafx.stage.Stage;

public interface NewGame {
    void newGame(Stage stage);

    void stop();

    void start(Stage primaryStage);
}
