package com.models.mapdesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;

import java.util.ArrayList;

public class Trash extends Actors {
    private Texture texture;
    private Floor floor;
    private Music trashSound;
    private boolean trashSoundPlaying = false;

    public Trash(int x, int y, Texture texture, Floor floor){
        super(x, y);
        this.texture = texture;
        this.floor = floor;
        this.width = 80;
        this.height = 80;
        trashSound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/TrashSound.mp3"));
        trashSound.setVolume(0.25f);
    }

    public Texture getTexture(){
        return texture;
    }

    public void deleteFood(){
        for(int i = 0; i < floor.getActors().size(); i++){
            if(floor.getActors().get(i) instanceof Food){
                if(!trashSoundPlaying) {
                    trashSound.play();
                    trashSoundPlaying = true;
                }
                floor.removeActors(floor.getActors().get(i));
                i--;
            }
        }
        trashSoundPlaying = false;
    }
}
