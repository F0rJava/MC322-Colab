package com.models.mapdesign;

import com.models.Actors;
import com.models.Food;

import java.util.ArrayList;

public class Floor {
    private int i;
    private int j;
    private ArrayList<Actors> actors;

    public Floor(int i, int j){
        this.actors = new ArrayList<>();
        this.i = i;
        this.j = j;
    }

    public ArrayList<Actors> getActors(){
        return actors;
    }

    public void addActors(Actors a){
        actors.add(a);
    }

    public boolean dontHaveActors(){
        if(actors.size() == 0)
            return true;
        return false;
    }

    public void removeActors(Actors o){
        actors.remove(o);
    }

    public Actors getFood(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Food)
                return  actors.get(i);
        }
        return null;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }
}
