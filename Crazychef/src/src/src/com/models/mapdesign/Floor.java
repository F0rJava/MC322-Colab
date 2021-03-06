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

    public Actors getGen(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Generator)
                return actors.get(i);
        }
        return null;
    }

    public Actors getTable(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Table)
                return actors.get(i);
        }
        return null;
    }

    public Actors getTrash(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Trash)
                return actors.get(i);
        }
        return null;
    }

    public Actors getOven(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Oven)
                return actors.get(i);
        }
        return null;
    }

    public Actors getOrderDelivery(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof OrderDelivery)
                return actors.get(i);
        }
        return null;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }

    public Actors getCutBoard(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof CutBoard)
                return actors.get(i);
        }
        return null;
    }

    public Actors getHole(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Hole)
                return actors.get(i);
        }
        return null;
    }

    public boolean haveHole(){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i) instanceof Hole)
                return true;
        }
        return false;
    }

    public void insertSorted(Food food){
        Food aux;
        int aux2 = actors.size();
        boolean gotInserted = false;
        if(aux2 == 0 || aux2 == 1 || aux2 == 2)
            actors.add(food);
        else if(aux2 >= 3){
            for(int i = 2; i < aux2; i++) {
                aux = (Food) actors.get(i);
                if (aux.getPrio() > food.getPrio()) {
                    actors.set(i, food);
                    insertSorted(aux);
                    gotInserted = true;
                    break;
                }
            }
            if(!gotInserted)
                actors.add(food);
        }
    }
}
