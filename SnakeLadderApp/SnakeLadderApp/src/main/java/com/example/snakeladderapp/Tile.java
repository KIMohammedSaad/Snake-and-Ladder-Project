package com.example.snakeladderapp;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {
    public Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.YELLOW);
        setStroke(Color.BLACK);
    }
}
