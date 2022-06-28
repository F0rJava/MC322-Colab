package com.models;

import com.badlogic.gdx.math.Rectangle;

//herdeiro de rectangle, para facilitar a posição e movimentação
public abstract class Actors extends Rectangle{
    private Rectangle actor;

    public Actors(int x, int y){
        this.x = x;
        this.y= y;
    }

    public Rectangle getActor(){
        return actor;
    }


}
