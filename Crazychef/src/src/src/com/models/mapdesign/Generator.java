package com.models.mapdesign;

import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;
import com.models.food.*;

public class Generator extends Actors {
    private String food;
    private Texture texture;

    public Generator(int x, int y, String food, Texture texture) {
        super(x, y);
        this.width = 80;
        this.height = 80;
        this.food = food;
        this.texture = texture;
    }

    public Food generateFood(){
        if(food.equalsIgnoreCase("Burger"))
            return new Burger(Math.round(this.x), Math.round(this.y));
        else if (food.equalsIgnoreCase("Bun"))
            return new Bun(Math.round(this.x), Math.round(this.y));
        else if(food.equalsIgnoreCase("Lettuce"))
            return new Lettuce(Math.round(this.x), Math.round(this.y));
        else if(food.equalsIgnoreCase("Cheese"))
            return  new Cheese(Math.round(this.x), Math.round(this.y));
        else if(food.equalsIgnoreCase("Tomato"))
            return new Tomato(Math.round(this.x),Math.round(this.y));
        else
            return new Plate(Math.round(this.x), Math.round(this.y));
    }

    public Texture getTexture(){
        return texture;
    }
}
