package com.models;

public class Kitchen {
    private Floor[][] floors;

    public Kitchen(int i, int j){
        this.floors = new Floor[i][j];

        for(int n = 0; n<i; n++){
            for(int m = 0; m<j; m++){
                floors[n][m] = new Floor();
            }
        }
    }

    public Floor getFloor(int i, int j){
        return floors[i][j];
    }

    public void setObjectsInFloor(Objects o, int i, int j){
        floors[i][j].addObjects(o);
    }
}
