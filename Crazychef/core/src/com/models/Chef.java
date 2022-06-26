package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.models.food.Plate;
import com.badlogic.gdx.graphics.Texture;
import com.models.mapdesign.Floor;

import java.util.ArrayList;

public class Chef extends Actors {
    //private ArrayList;
    private Plate plate;
    private ArrayList<Food> hand;
    private boolean[] orientation;
    //texturas do chef
    private Texture chefFront;
    private Texture chefBack;
    private Texture chefLeft;
    private Texture chefRight;

    public Chef(int x, int y) {
        super(x,y);//posição onde o chefe inicia
        this.width = 80;
        this.height = 140;
        this.plate = null;
        this.hand = new ArrayList<>();

        orientation = new boolean[4];
        orientation[0] = true; //chefFront
        orientation[1] = false;//chefBack
        orientation[2] = false;//chefLeft
        orientation[3] = false;//chefRight

        chefFront = new Texture(Gdx.files.internal("Chef/chefFront.png"));//textura do chef
        chefBack = new Texture(Gdx.files.internal("Chef/chefBack.png"));
        chefRight = new Texture(Gdx.files.internal("Chef/chefRight.png"));
        chefLeft = new Texture(Gdx.files.internal("Chef/chefLeft.png"));
    }

    public void setOrientation(int i){
        for(int j = 0; j < 4; j++){
            if(orientation[j])
                orientation[j] = false;
        }
        orientation[i] = true;
    }

    public boolean getOrientation(int i){
        return orientation[i];
    }

    public Texture getTexture(){
        if(orientation[0])
            return chefFront;
        else if(orientation[1])
            return chefBack;
        else if(orientation[2])
            return chefLeft;
        else
            return chefRight;
    }

    public ArrayList<Food> getHand(){
        return hand;
    }

    public void updateActorsCoordinates(){
        for(int i = 0; i < hand.size(); i++){
            hand.get(i).setX(this.x);
            hand.get(i).setY(this.y);
            if(orientation[0])
                hand.get(i).setOrientation(0, 10);
            else if (orientation[1])
                hand.get(i).setOrientation(0, 0);
            else if (orientation[2])
                hand.get(i).setOrientation(-20, 20);
            else if (orientation[3])
                hand.get(i).setOrientation(20, 20);
        }
    }

    public boolean hold(Food food){
        if(food != null) {
            if (hand.size() == 0) {
                hand.add(food);
                return true;
            } else if (hand.get(0) instanceof Plate && !(food instanceof Plate)) {
                hand.add(food);
                return true;
            }
        }
        return false;
    }

    public void release(Floor floor){
        if(floor != null){
            //solta a comida em qualquer lugar do mapa
            for(int i = 0; i < hand.size(); i++){
                hand.get(i).setOrientation(0, 10);
                hand.get(i).setX(floor.getJ()*80);
                hand.get(i).setY(floor.getI()*80);
                floor.addActors(hand.get(i));
            }
            int aux = hand.size();
            for(int i = 0; i < aux; i++){
                hand.remove(0);
            }
        }
    }

}
