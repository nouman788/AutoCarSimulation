package com.simulation;

import java.io.IOException;
import java.util.Arrays;
 

public class Validation {


    public boolean validFieldData(String[] field) throws IOException{
        if (field.length < 2 || field.length > 2){
            throw new IOException(" field data is incorrect");
        }
        return true;
    }

    public boolean validCarData(String[] carData,Field field) throws IOException{
        
        String[] d = {"N","S","E","W"}; 

        if (carData.length < 3 || carData.length > 3){
            throw new IOException(" field data is incorrect");
        }
        if (Integer.parseInt(carData[0])< 0 || Integer.parseInt(carData[0]) > field.xLength-1 || Integer.parseInt(carData[1]) < 0 || Integer.parseInt(carData[1]) > field.yLength-1 ){
            throw new IOException(" Car x and y position should be between: 0 "+(field.xLength-1));
        }
        if (!Arrays.asList(d).contains(carData[2])){
            throw new IOException(" Car position data is incorrect");
        }
        return true;
    }


    
}
