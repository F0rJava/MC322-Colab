package com.models;

public class Floor {
    private Objects[] objects;

    public Floor(){
        this.objects = new Objects[2];
    }

    public Objects[] getObjects(){
        return objects;
    }

    public void addObjects(Objects o){
        if(objects[0] == null)
            objects[0] = o;
        else
            objects[1] = o;
    }

    public boolean dontHaveObjects(){
        if(objects[0] == null && objects[1] == null)
            return true;
        return false;
    }

    public void removeObjects(Objects o){
        if(objects[0] == o)
            objects[0] = null;
        else if (objects[1] == o)
            objects[1] = null;
    }
}
