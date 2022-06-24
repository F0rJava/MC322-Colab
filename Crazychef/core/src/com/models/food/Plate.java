package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;

public class Plate extends Food {

    public Plate(int x, int y) {
        super(x, y, new Texture(Gdx.files.internal("Food/Level1/plate.png")));
    }
}
