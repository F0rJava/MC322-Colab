package com.models.mapdesign;

import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.mapdesign.Floor;

public class Kitchen {
    private Floor[][] floors;
    private Texture texture;

    public Kitchen(int i, int j, Texture texture){
        this.floors = new Floor[i][j];
        this.texture = texture;

        for(int n = 0; n<i; n++){
            for(int m = 0; m<j; m++){
                floors[n][m] = new Floor(n, m);
            }
        }
    }

    public Floor getFloor(int i, int j){
        return floors[i][j];
    }

    public void setObjectsInFloor(Actors o, int i, int j){
        floors[i][j].addActors(o);
    }
    public Texture getTexture(){
        return texture;
    }
}
