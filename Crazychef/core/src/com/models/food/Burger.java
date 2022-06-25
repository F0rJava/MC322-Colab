package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;
import org.w3c.dom.Text;

public class Burger extends Food {
    Integer timeUntilCooked = 10;
    Integer timeToBurn = 20;
    public Burger(int x, int y){
        super(x,y, new Texture(Gdx.files.internal("Food/Level1/rawBurger.png")));
    }

    public void isCooking(float dt){

    }
}
