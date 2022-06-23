package com.models;

public class Chef extends Objects {
    //private ArrayList;
    private Plate plate;

    public Chef(int x, int y) {
        //posição onde o chefe inicia
        super(x,y);
        this.width = 80;
        this.height = 120;
    }
}
