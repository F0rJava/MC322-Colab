package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;
import org.w3c.dom.Text;

public class Burger extends Food {
    private Integer timeUntilCooked;
    private Integer timeToBurn;
    private float timeCount;
    public Burger(int x, int y){
        super(x,y, new Texture(Gdx.files.internal("Food/Level1/rawBurger.png")));
        this.cookable = true;
        this.timeToBurn = 20;
        this.timeUntilCooked = 10;
    }

    public void updateTime(float dt){
        timeCount+=dt;
        if (timeUntilCooked>0){
            if(timeCount>=1){
                timeUntilCooked--;
                timeToBurn--;
                timeCount = 0;
            }
        }
        if(timeUntilCooked == 0){
            this.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/cookedBurger.png")));
            super.setCooked(true);
        }
        if(timeUntilCooked == 0 && timeToBurn == 0){
            this.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/toastedBurger.png")));
            super.setCooked(false);
        }
        if(timeToBurn>0){
            if(timeCount>=1){
                timeToBurn--;
                timeCount = 0;
            }
        }
    }
}
