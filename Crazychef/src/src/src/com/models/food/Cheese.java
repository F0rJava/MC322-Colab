package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;

public class Cheese extends Food {
    public Cheese(int x, int y){
        super(x, y, new Texture(Gdx.files.internal("Food/Level1/cheese.png")));
        this.cookable = false;
        this.cuttable = false;
        this.prio = 1;
    }
}
