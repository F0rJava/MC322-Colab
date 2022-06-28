package com.models;

import com.badlogic.gdx.Gdx;
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
    private boolean canMove = true;

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
        //chefFront = new Texture(Gdx.files.internal("Chef/chefFront.png"));
        chefFront = new Texture(Gdx.files.internal("Food/vanish.png"));//textura do chef
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

    public boolean canHold(Food food){
        if(food != null) {
            if (hand.size() == 0)
                return true;
            else if (hand.get(0) instanceof Plate && !(food instanceof Plate))
                return true;
        }
        return false;
    }

    public void insertSorted(Food food){
        Food aux;
        int aux2 = hand.size();
        boolean gotInserted = false;
        if(aux2 == 0 || aux2 == 1)
            hand.add(food);
        else if(aux2 >= 2){
            for(int i = 1; i < aux2; i++) {
                if (hand.get(i).getPrio() > food.getPrio()) {
                    aux = hand.get(i);
                    hand.set(i, food);
                    insertSorted(aux);
                    gotInserted = true;
                    break;
                }
            }
            if(!gotInserted)
                hand.add(food);
        }
    }

    public void release(Floor floor){
        if(floor != null){
            //solta a comida em qualquer lugar do mapa
            for(int i = 0; i < hand.size(); i++){
                hand.get(i).setOrientation(0, 10);
                hand.get(i).setX(floor.getJ()*80);
                hand.get(i).setY(floor.getI()*80);
                floor.insertSorted(hand.get(i));
            }
            int aux = hand.size();
            for(int i = 0; i < aux; i++){
                hand.remove(0);
            }
        }
    }

    public void removeFromHand(int i){
        hand.remove(i);
    }

    public void setCanMove(Boolean b){
        this.canMove = b;
    }

    public boolean getCanMove(){return canMove;}
}
