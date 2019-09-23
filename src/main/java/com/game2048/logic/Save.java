package com.game2048.logic;

import java.io.*;

public class Save {
    private static final String filepath="C:\\Users\\HP\\IdeaProjects\\2048GameJavaFX\\src\\main\\resources\\save";

    public void writeObjectToFile(int[][] boardState){

        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filepath));
            objectOut.writeObject(boardState);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int[][] readObjectFromFile(){
        int[][] boardState = null;
        try {
            ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream(filepath));
            boardState = (int[][])fileInput.readObject();
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return boardState;
    }
}
