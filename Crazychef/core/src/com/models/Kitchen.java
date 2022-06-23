package com.models;

public class Kitchen {
    private Objects[][] objects;

    public Kitchen(int i, int j){
        this.objects = new Objects[i][j];
    }

    public Objects getObjects(int i, int j){
        return objects[i][j];
    }

    public void setObjects(Objects o, int i, int j){
        objects[i][j] = o;
    }
}
