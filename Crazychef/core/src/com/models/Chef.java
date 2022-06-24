package com.models;

import com.badlogic.gdx.Gdx;
import com.models.mapdesign.Plate;
import com.badlogic.gdx.graphics.Texture;

import java.io.ObjectStreamException;

public class Chef extends Objects {
    //private ArrayList;
    private Plate plate;
    private boolean[] orientation;

    //texturas do chef
    private Texture chefFront;
    private Texture chefBack;
    private Texture chefLeft;
    private Texture chefRight;

    public Chef(int x, int y) {
        //posição onde o chefe inicia
        super(x,y);
        this.width = 80;
        this.height = 140;
        this.plate = null;

        orientation = new boolean[4];
        orientation[0] = true; //chefFront
        orientation[1] = false;//chefBack
        orientation[2] = false;//chefLeft
        orientation[3] = false;//chefRight

        chefFront = new Texture(Gdx.files.internal("Chef/chefFront.png"));//textura do chef
        chefBack = new Texture(Gdx.files.internal("Chef/chefBack.png"));
        chefRight = new Texture(Gdx.files.internal("Chef/chefRight.png"));
        chefLeft = new Texture(Gdx.files.internal("Chef/chefLeft.png"));
    }

    public void setOrientation(int i){
        for(int j = 0; j < 4; j++){
            if(orientation[j])
                orientation[j] = false;
        }
        orientation[i] = true;
    }

    public boolean getOrientation(int i){
        return orientation[i];
    }

    public Texture getTexture(){
        if(orientation[0])
            return chefFront;
        else if(orientation[1])
            return chefBack;
        else if(orientation[2])
            return chefLeft;
        else
            return chefRight;
    }
}
