package com.models.mapdesign;

import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Chef;

public class Hole extends Actors {
    private Texture texture;
    private Floor floor, teleportedTo;
    private Chef chef;

    public Hole(int x, int y, Texture texture, Chef chef, Floor floor, Floor teleportedTo){
        super(x, y);
        this.texture = texture;
        this.width = 80;
        this.height = 80;
        this.chef = chef;
        this.floor = floor;
        this.teleportedTo = teleportedTo;
    }

    public Texture getTexture(){
        return texture;
    }

    public void fellInTheHole(){
        if(chef.x == floor.getJ()*80 && chef.y == floor.getI()*80) {
            for(int i = 0; i < chef.getHand().size(); i++) {
                chef.removeFromHand(i);
                i--;
            }
            floor.removeActors(chef);
            teleportedTo.addActors(chef);
            chef.setX(teleportedTo.getJ() * 80);
            chef.setY(teleportedTo.getI() * 80);
        }
    }
}
