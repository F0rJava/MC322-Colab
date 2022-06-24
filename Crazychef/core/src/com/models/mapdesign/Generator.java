package com.models.mapdesign;

import com.badlogic.gdx.graphics.Texture;
import com.models.Objects;
import com.models.food.Burger;

public class Generator extends Objects {
    private String food;
    private Texture texture;

    public Generator(int x, int y, String food, Texture texture) {
        super(x, y);
        this.width = 80;
        this.height = 80;
        this.food = food;
        this.texture = texture;
    }

    public Objects generateFood(){
        //if(food.equalsIgnoreCase("Burger"))
            return new Burger(Math.round(this.x), Math.round(this.y));
    }

    public Texture getTexture(){
        return texture;
    }
}