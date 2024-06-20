package com.unitTest;

import org.junit.Assert;
import org.junit.Test;

import com.simulation.*;

public class CarTest {
    
    String[] cmd = {"F", "F"};
    Car car  = new Car("A",1, 2,"N", cmd);

    @Test
    public void testMoveForward() {
        car.moveForward();
        Assert.assertTrue("Incorrect Y Position", car.getYPosition() ==3);
        car.turnRight();
        car.moveForward();
        Assert.assertTrue("Incorrect X Position", car.getXPosition() ==2);
        car.turnRight();
        car.moveForward();
        Assert.assertTrue("Incorrect Y Position", car.getYPosition() ==2);
        car.turnRight();
        car.moveForward();
        Assert.assertTrue("Incorrect X Position", car.getXPosition() ==1);

    }

    @Test
    public void testTurnLeft() {

        car.turnLeft();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("W"));
        car.turnLeft();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("S"));
        car.turnLeft();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("E"));
        car.turnLeft();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("N"));
        car.turnLeft();

    }

    @Test
    public void testTurnRight() {

        car.turnRight();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("E"));
        car.turnRight();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("S"));
        car.turnRight();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("W"));
        car.turnRight();
        Assert.assertTrue("Incorrect Direction", car.getDirection().equals("N"));


    }
}
