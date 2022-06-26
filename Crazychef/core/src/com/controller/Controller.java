package com.controller;

import com.models.*;
import com.models.food.Plate;
import com.models.mapdesign.Kitchen;

import java.util.ArrayList;

//classe responsavel por controlar as views do jogo e os models
public class Controller{
    private Chef chef;
    private Kitchen kitchen;
    private Integer levelTime;
    private float timeCount;

    //movimenta o chef, checando se não há colisões com outros objetos
    public void up(){
        if(chef.getCanMove()) {
            chef.setOrientation(1);
            chef.updateActorsCoordinates();
            if (Math.round(chef.y / 80) + 1 < 9 && kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).dontHaveActors()) {
                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
                kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).addActors(chef); //adiciona o chef na nova posição
                chef.y += 80; //atualiza o atributo y do chef para a nova posição
            }
        }
    }
    public void down(){
        if(chef.getCanMove()) {
            chef.setOrientation(0);
            chef.updateActorsCoordinates();
            if (Math.round(chef.y / 80) - 1 >= 0 && kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).dontHaveActors()) {
                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
                kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).addActors(chef); //adiciona o chef na nova posição
                chef.y -= 80; //atualiza o atributo y do chef para a nova posição
            }
        }
    }
    public void left(){
        if(chef.getCanMove()){
            chef.setOrientation(2);
            chef.updateActorsCoordinates();
            if (Math.round(chef.x / 80) - 1 >= 0 && kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1).dontHaveActors()) {
                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1).addActors(chef); //adiciona o chef na nova posição
                chef.x -= 80; //atualiza o atributo x do chef para a nova posição
            }
        }
    }
    public void right(){
        if(chef.getCanMove()) {
            chef.setOrientation(3);
            chef.updateActorsCoordinates();
            if (Math.round(chef.x / 80) + 1 < 16 && kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).dontHaveActors()) {
                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).addActors(chef); //adiciona o chef na nova posição
                chef.x += 80; //atualiza o atributo x do chef para a nova posição
            }
        }
    }

    public boolean canPickup(Food food){
        if (chef.getHand().size() == 0)
            return true;
        else if(chef.getHand().size() >= 1 && chef.getHand().get(0) instanceof Plate){
            if((food.getCookable() && food.getCooked()) || (food.getCuttable() && food.getCut()))
                return true;
            else if(!food.getCookable() && !food.getCuttable())
                return true;
        }
        return false;
    }

    public boolean canRelease(Food food){
        if(chef.getHand().size() == 1 && (food == null || food instanceof Plate)){
            if(chef.getHand().get(0).getCookable()){
                if(chef.getHand().get(0).getCooked())
                    return true;
                else if (!(food instanceof Plate))
                    return true;
                return false;
            } else if (chef.getHand().get(0).getCuttable()) {
                if(chef.getHand().get(0).getCut())
                    return true;
                else if (!(food instanceof Plate))
                    return true;
                return false;
            } else if(chef.getHand().get(0) instanceof Plate && food instanceof Plate)
                return false;
            else
                return true;
        }
        else if (chef.getHand().size() > 1 && food == null)
            return true;
        return false;
    }

    public void pickup(){
        ArrayList<Actors> aux1 = null;
        Food aux2 = null;

        if(chef.getOrientation(0)){
            if(Math.round(chef.y/80)-1 >= 0) {
                aux1 = kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).getActors();
                for(int i = 0; i < aux1.size(); i++){
                    if(aux1.get(i) instanceof Food){
                        aux2 = (Food) aux1.get(i);
                        if(canPickup(aux2)) {
                            if (chef.canHold(aux2)) {
                                chef.insertSorted(aux2);
                                kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).removeActors(aux2);
                                i--;
                            }
                        }
                    }
                }
            }
        }

        else if (chef.getOrientation(1)){
            if(Math.round(chef.y/80)+1 < 9) {
                aux1 = kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round((chef.x / 80))).getActors();
                for(int i = 0; i < aux1.size(); i++){
                    if(aux1.get(i) instanceof  Food){
                        aux2 = (Food) aux1.get(i);
                        if(canPickup(aux2)) {
                            if (chef.canHold(aux2)) {
                                chef.insertSorted(aux2);
                                kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).removeActors(aux2);
                                i--;
                            }
                        }
                    }
                }
            }
        }

        else if (chef.getOrientation(2)) {
            if(Math.round(chef.x/80)-1 >= 0) {
                aux1 = kitchen.getFloor(Math.round(chef.y / 80), Math.round((chef.x / 80)) - 1).getActors();
                for(int i = 0; i < aux1.size(); i++){
                    if(aux1.get(i) instanceof  Food){
                        aux2 = (Food) aux1.get(i);
                        if(canPickup(aux2)) {
                            if (chef.canHold(aux2)) {
                                chef.insertSorted(aux2);
                                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1).removeActors(aux2);
                                i--;
                            }
                        }
                    }
                }
            }
        }

        else {
            if (Math.round(chef.x / 80) + 1 < 16) {
                aux1 = kitchen.getFloor(Math.round(chef.y / 80), Math.round((chef.x / 80)) + 1).getActors();

                for(int i = 0; i < aux1.size(); i++){
                    if(aux1.get(i) instanceof  Food){
                        aux2 = (Food) aux1.get(i);
                        if(canPickup(aux2)) {
                            if (chef.canHold(aux2)) {
                                chef.insertSorted(aux2);
                                kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).removeActors(aux2);
                                i--;
                            }
                        }
                    }
                }
            }
        }
        chef.updateActorsCoordinates();
    }

    public void release(){
        Food aux;

        if(chef.getOrientation(0)){
            if(Math.round(chef.y/80)-1 >= 0) {
                aux = (Food) kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).getFood();
                if(this.canRelease(aux))
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)));
            }
        }
        else if (chef.getOrientation(1)){
            if(Math.round(chef.y/80)+1 < 9) {
                aux = (Food) kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).getFood();
                if(this.canRelease(aux))
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)));
            }
        }
        else if (chef.getOrientation(2)) {
            if(Math.round(chef.x/80)-1 >= 0) {
                aux = (Food) kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1).getFood();
                if(this.canRelease(aux))
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1));
            }
        }
        else {
            if (Math.round(chef.x / 80) + 1 < 16) {
                aux = (Food) kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).getFood();
                if(this.canRelease((aux)))
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1));
            }
        }
    }

    public void setChef(Chef chef){
        this.chef = chef;
    }

    public void setKitchen(Kitchen cozinha){
        this.kitchen = cozinha;
    }

    public void setLevelTime(int sec) {
        this.levelTime = sec;
    }

    public Integer getLevelTime(){
        return this.levelTime;
    }

    public void updateTime(float dt){
        timeCount+=dt;
        if (levelTime>0){
            if(timeCount>=1){
                levelTime--;
                timeCount = 0;
            }
        }
    }
}
