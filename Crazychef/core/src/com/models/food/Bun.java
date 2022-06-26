package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;

public class Bun extends Food {
    public Bun(int x, int y){
        super(x, y, new Texture(Gdx.files.internal("Food/Level1/bun.png")));
        this.cookable = false;
        this.prio = 5;
    }
}
