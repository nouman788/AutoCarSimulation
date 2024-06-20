package com.simulation;


public class Field {

    public String[][] simulationFieldPoints;
    public int xLength = 0;
    public int yLength = 0;


     public Field(int x , int y)
    {
        simulationFieldPoints  = new String[x][y];
        this.xLength = x;
        this.yLength = y;
    }

    public void addCar(int x , int y, String name)
    {
        simulationFieldPoints[x][y] = name;
    }

    public void removeCar(int x , int y)
    {
        simulationFieldPoints[x][y] = null;
    }

    public String getCar(int x , int y)
    {
        return simulationFieldPoints[x][y];
    }

    

    
}
