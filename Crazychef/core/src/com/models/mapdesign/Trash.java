package com.models.mapdesign;

import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;

import java.util.ArrayList;

public class Trash extends Actors {
    private Texture texture;
    private Floor floor;

    public Trash(int x, int y, Texture texture, Floor floor){
        super(x, y);
        this.texture = texture;
        this.floor = floor;
        this.width = 80;
        this.height = 80;
    }

    public Texture getTexture(){
        return texture;
    }

    public void deleteFood(){
        for(int i = 0; i < floor.getActors().size(); i++){
            if(floor.getActors().get(i) instanceof Food){
                floor.removeActors(floor.getActors().get(i));
                i--;
            }
        }
    }
}
