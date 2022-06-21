package com.models;

import com.badlogic.gdx.math.Rectangle;

//herdeiro de rectangle, para facilitar a posição e movimentação
public class Objetos extends Rectangle{
    private Rectangle objeto;

    public Objetos(int x, int y){
        objeto.x = x;
        objeto.y= y;
    }

    public Rectangle getObjeto(){
        return objeto;
    }
}
