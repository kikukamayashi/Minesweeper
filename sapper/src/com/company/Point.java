package com.company;

import java.util.Random;

public class Point {
    private int x;
    private int y;
    private int countBombAround;
    private boolean  isBomb;
    private boolean isOpen;


    public void Point(){
        this.countBombAround = 0;
        this.isBomb = isBomb;
        this.isOpen = false;
    }

    public void Point(int x,int y){
        this.x = x;
        this.y = y;
        this.countBombAround = 0;
        this.isBomb = isBomb;
        this.isOpen = false;
    }


    public void setBomb(boolean flag){
        this.isBomb = flag;
    }

    public void plusCountBombAround() {
        countBombAround ++;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open(){ this.isOpen = true; }

    public int getCountBombAround(){
        return countBombAround;
    }




}
