package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;

public class Tomato extends Food {
    private Integer timeToCut;
    private float timeCount;
    public Tomato(int x, int y){
        super(x, y, new Texture(Gdx.files.internal("Food/Level1/tomato.png")));
        this.cuttable = true;
        this.timeToCut = 3;
        this.prio = 3;
    }

    public void updateTime(float dt){
        timeCount+=dt;
        if (timeToCut>0){
            if(timeCount>=1){
                timeToCut--;
                timeCount = 0;
            }
        }
        if(timeToCut == 0){
            this.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/tomatoCut.png")));
            super.setCut();
        }
    }
}
