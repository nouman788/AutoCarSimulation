package com.simulation;

public class Car
{

    private String name;
    private String[] command;
    private int x;
    private int y;
    private String d;

    public static final String NORTH = "N";
    public static final String SOUTH = "S";
    public static final String EAST = "E";
    public static final String WEST = "W";
    
    public Car(String carName, int theX, int theY, String theDirection, String[] carCommand)
    {
        this.name = carName;
        this.command = carCommand;
        this.x = theX;
        this.y = theY;
        this.d = theDirection;
    }   

    public void moveForward()
    {
        switch (d) {
            case NORTH -> y = y+1;
            case WEST -> x = x-1;
            case SOUTH -> y = y-1;
            case EAST -> x = x+1;
        }   
    }

    public void turnLeft()
    {
        switch (d) {
            case NORTH -> d = WEST;
            case WEST -> d = SOUTH;
            case SOUTH -> d = EAST;
            case EAST -> d = NORTH;
        }
    }

    public void runCommande(String commmand)
    {
        switch (commmand) {
            case "F" -> this.moveForward();
            case "R" -> this.turnRight();
            case "L" -> this.turnLeft();
        }   
    }

    public void turnRight()
    {
        switch (d) {
        case NORTH -> d = EAST;
        case EAST -> d = SOUTH;
        case SOUTH -> d = WEST;
        case WEST -> d = NORTH;
        }    
    }
    public String getDirection()
    {  
        switch (d) {
            case NORTH -> {
                return "N";
                }
            case WEST -> {
                return "W";
                }
            case SOUTH -> {
                return "S";
                }
            case EAST -> {
                return "E";
                }
        }
        return "";
    }

    public Integer getXPosition()
    {
        return this.x;   
    }

    public Integer getYPosition()
    {
        return this.y;  
    }

    public String[] getCommands()
    {
        return this.command;
    }

    public String getNextCommand(int stage)
    {
        return this.command[stage];
    }

    public String getName()
    {
        return this.name;
    }

    public void setCommand(String[] command) {
        this.command = command;
    }

    public void setName(String name) {
        this.name = name;
    }

}