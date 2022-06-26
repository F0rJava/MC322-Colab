package com.models.mapdesign;

import com.models.Actors;

public class Oven extends Actors {
    public Oven(int x, int y){
        super(x,y);
        this.width = 80;
        this.height = 80;
    }

    public void startCooking(Food food, float dt){
        food.updateTime(dt);
    }
}
