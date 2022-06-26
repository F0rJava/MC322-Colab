package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Food extends Actors{
    protected int[] orientation;
    protected Texture baseTexture;
    protected Texture vanish;
    protected boolean cookable = false;
    protected boolean isCooked = false;

    public Food(int x, int y, Texture baseTexture){
        super(x, y);
        this.orientation = new int[2];
        this.baseTexture = baseTexture;
        this.width = 80;
        this.height = 80;
        this.vanish = new Texture(Gdx.files.internal("Food/vanish.png"));
    }

    public void setOrientation(int pixelsX, int pixelsY){
        this.orientation[0] = pixelsX;
        this.orientation[1] = pixelsY;
    }

    public int[] getOrientation(){
        return orientation;
    }

    public boolean getCookable(){
        return this.cookable;
    }

    public void setBaseTexture(Texture nbaseTexture){
        this.baseTexture = nbaseTexture;
    }

    public Texture getVanishTexture(){
        return vanish;
    }

    public Texture getBaseTexture(){
        return baseTexture;
    }

    public boolean getCooked(){
        return isCooked;
    }

    public void setCooked(boolean cooked){
        this.isCooked = cooked;
    }

    public void updateTime(float dt){

    }
}
