package com.example.snakeladderapp;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCooridinates;

    ArrayList<Integer> snakeLadderPosition;

    public Board(){
        positionCooridinates = new ArrayList<>();
        populatePositionCooridinates();
        populateSnakeLadder();
    }

    private void populatePositionCooridinates(){
        positionCooridinates.add(new Pair<>(0,0));//Dummy Value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //x coordinates
                int xCord = 0;
                if(i % 2 == 0){
                    xCord = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }else{
                    xCord = SnakeLadder.tileSize *SnakeLadder.height - (j*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                }
                //y coordinates
                int yCord = SnakeLadder.tileSize *SnakeLadder.height - (i*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                positionCooridinates.add(new Pair<>(xCord,yCord));

            }
        }
    }

    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(2,23);
        snakeLadderPosition.set(6,45);
        snakeLadderPosition.set(20,59);
        snakeLadderPosition.set(30,16);
        snakeLadderPosition.set(37,3);
        snakeLadderPosition.set(52,72);
        snakeLadderPosition.set(56,8);
        snakeLadderPosition.set(57,96);
        snakeLadderPosition.set(75,94);
        snakeLadderPosition.set(78,98);
        snakeLadderPosition.set(84,64);
        snakeLadderPosition.set(87,31);
        snakeLadderPosition.set(98,40);
    }

    public int getNewPosition(int currentPosition){
        if(currentPosition > 0 && currentPosition <= 100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
    int getXCoordinate(int position){
        if(position>=1 && position<=100)
            return positionCooridinates.get(position).getKey();
        return -1;
    }
    int getYCoordinate(int position){
        if(position>=1 && position<=100)
            return positionCooridinates.get(position).getValue();
        return -1;
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.positionCooridinates.size(); i++) {
//            System.out.println(i + " $ x :" + board.positionCooridinates.get(i).getKey()
//            + " y : " + board.positionCooridinates.get(i).getValue());
//
//        }
//    }
}
