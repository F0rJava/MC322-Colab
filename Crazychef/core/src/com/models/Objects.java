package com.models;

import com.badlogic.gdx.math.Rectangle;

//herdeiro de rectangle, para facilitar a posição e movimentação
public class Objects extends Rectangle{
    private Rectangle object;


    public Objects(int x, int y){
        this.x = x;
        this.y= y;
    }

    public Rectangle getObject(){
        return object;
    }


}
