package com.models.mapdesign;

import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;

public class Oven extends Actors {
    Texture texture;
    public Oven(int x, int y, Texture texture){
        super(x,y);
        this.width = 80;
        this.height = 80;
        this.texture = texture;
    }

    public Texture getTexture(){
        return this.texture;
    }

    public void startCooking(Food food, float dt){
        food.updateTime(dt);
    }
}
