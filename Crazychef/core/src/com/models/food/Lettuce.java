package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;

public class Lettuce extends Food {
    private Integer timeToCut;
    private float timeCount;
    private Music Cutting;
    private boolean CuttingPlaying = false;
    public Lettuce(int x, int y){
        super(x, y, new Texture(Gdx.files.internal("Food/Level1/lettuce.png")));
        this.cuttable = true;
        this.timeToCut = 3;
        this.prio = 2;
        this.Cutting = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Cutting.mp3"));
        Cutting.setVolume(0.25f);
    }

    public void updateTime(float dt){
        timeCount+=dt;
        if (timeToCut>0){
            if(!CuttingPlaying){
                Cutting.play();
                CuttingPlaying = true;
            }
            if(timeCount>=1){
                timeToCut--;
                timeCount = 0;
            }
        }
        if(timeToCut == 0){
            this.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/LettuceCut.png")));
            super.setCut();
        }
    }
}
