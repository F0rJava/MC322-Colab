package com.models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;
import com.badlogic.gdx.audio.Music;

public class Burger extends Food {
    private Integer timeUntilCooked;
    private Integer timeToBurn;
    private float timeCount;
    private Music OvenTimer;
    private Music OvenCooked;
    private Music OvenAlarm;
    private boolean OvenAlarmPlaying = false;
    private boolean OvenTimerPlaying = false;
    private boolean OvenCookedPlaying = false;
    public Burger(int x, int y){
        super(x,y, new Texture(Gdx.files.internal("Food/Level1/rawBurger.png")));
        this.cookable = true;
        this.timeToBurn = 20;
        this.timeUntilCooked = 10;
        this.prio = 0;
        this.OvenTimer = Gdx.audio.newMusic(Gdx.files.internal("Sounds/OvenTimer.mp3"));
        this.OvenCooked = Gdx.audio.newMusic(Gdx.files.internal("Sounds/OvenCooked.mp3"));
        this.OvenAlarm = Gdx.audio.newMusic(Gdx.files.internal("Sounds/OvenAlarm.mp3"));
        OvenTimer.setVolume(0.25f);
        OvenCooked.setVolume(0.25f);
        OvenAlarm.setVolume(0.25f);
    }

    public void updateTime(float dt){
        timeCount+=dt;
        if (timeUntilCooked>0){
            if(!OvenTimerPlaying){
                OvenTimer.play();
                OvenTimerPlaying = true;
            }
            if(timeCount>=1){
                timeUntilCooked--;
                timeToBurn--;
                timeCount = 0;
            }
        }
        if(timeUntilCooked == 0 && timeToBurn == 0){
            this.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/toastedBurger.png")));
            super.setCooked(false);
            if(!OvenAlarmPlaying) {
                OvenAlarm.play();
                OvenAlarmPlaying = true;
            }
        }
        else if(timeUntilCooked == 0){
            OvenTimer.stop();
            OvenTimerPlaying = false;
            if(!OvenCookedPlaying) {
                OvenCooked.play();
                OvenCookedPlaying = true;
            }
            this.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/cookedBurger.png")));
            super.setCooked(true);
        }
        if(timeToBurn>0){
            if(timeCount>=1){
                timeToBurn--;
                timeCount = 0;
            }
        }
    }
}
