package com.models;

import com.models.mapdesign.Plate;

public class Chef extends Objects {
    //private ArrayList;
    private Plate plate;
    private boolean[] orientation;

    public Chef(int x, int y) {
        //posição onde o chefe inicia
        super(x,y);
        this.width = 80;
        this.height = 140;
        orientation = new boolean[4];
        orientation[0] = true; //chefFront
        orientation[1] = false;//chefBack
        orientation[2] = false;//chefLeft
        orientation[3] = false;//chefRight
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
}
