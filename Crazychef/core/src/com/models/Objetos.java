package com.models;

import com.badlogic.gdx.math.Rectangle;

//herdeiro de rectangle, para facilitar a posição e movimentação
public class Objetos extends Rectangle{
    private Rectangle objeto;

    public Objetos(int x, int y){
        this.x = x;
        this.y= y;
    }

    public Rectangle getObjeto(){
        return objeto;
    }
}
