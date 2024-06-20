package com.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Simulation
{
    
    public static final String ADD = "1";
    public static final String SIIMULATION = "2";
    
    public int commandLength = 0;

    Field field;
    Validation validation;
    ArrayList<Car> cars;
    BufferedReader reader;


    public Simulation()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));
        validation = new Validation();
    }
    
    public void startSimulation(){
        cars = new ArrayList<>();
        try{
            
            System.out.print("\033\143");
            System.out.println("Welcome to Auto Driving Car Simulation! ");
            System.out.println("Please Enter width and height of the simulation field in x y format: ");
            String[] simulationField = reader.readLine().trim().split(" ");
            if(validation.validFieldData(simulationField))
                field = new Field(Integer.parseInt(simulationField[0]), Integer.parseInt(simulationField[1]));
            System.out.println("You have created a field of "+simulationField[0]+" x "+simulationField[1]+".");
            System.out.println("");
            simulaionOptions();
            
        }catch(IOException | NumberFormatException e){

            System.out.println("wrong data entered");
            System.out.println(e.getMessage());
            simulaionRestart();

        }
        
    }
    
    public void simulaionOptions(){
       
        try{
            displayCars(false);
            System.out.println("");
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Add a car to field");
            System.out.println("[2] Run simulation");
            String option = reader.readLine();
            switch (option) {
                case ADD -> addCar();
                case SIIMULATION -> runCarSiimulation();
            } 
        
        }catch(IOException e){
            System.out.println("wrong data entered");
            System.out.println(e.getMessage());
            simulaionRestart();
        }
    }
    
    public void addCar(){
        Car car;
        int positionX = 0;
        int positionY = 0;
        String carDirection =  "";
        try{
            
            System.out.print("\033\143");
            System.out.println("Please enter the name of the car:");
            String name = reader.readLine();
            System.out.println("Please enter initial position of car "+name+" in x y Direction format:");
            String[] position = reader.readLine().trim().split(" ");
            if(validation.validCarData(position,field))
            {
                positionX  = Integer.parseInt(position[0]);
                positionY  = Integer.parseInt(position[1]);
                carDirection = position[2];
            }

            System.out.println("Please Enter command of the Car "+name+": ");
            String[] commandArray = reader.readLine().trim().toUpperCase().split("");

            if(commandArray.length > commandLength)
                    commandLength = commandArray.length;
            
            if(field.getCar(positionX, positionY) == null){
                car = new Car(name,positionX,positionY,carDirection,commandArray);
                cars.add(car);
                field.addCar(positionX, positionY,name);
            }
            else{
                System.out.println("Car "+field.getCar(positionX, positionY)+" is at this position");
            }
            simulaionOptions();
            
        }catch(IOException | ArrayIndexOutOfBoundsException | NumberFormatException e){
            System.out.println("wrong car data entered");
            System.out.println(e.getMessage());
            simulaionRestart();
        }
        
    }

    public void runCarSiimulation(){
        
        if(cars.size() <=0)
        {
            System.out.println("There are no cars for simulation");
            simulaionOptions();
        }
        System.out.println("After simulation, the result is:");
        if(cars.size()>1)
            runMultCarSiimulation();
        else 
            runSingleCarSimulation();
       
    }
    
    public void runMultCarSiimulation(){
        
         int i =0;
         int j;
         String nextCommand;
         Car car;
         while (i < commandLength) {
          j = 0 ;
          while(j < cars.size()){
            car = cars.get(j);
            if(i < car.getCommands().length)
            {
                nextCommand = car.getNextCommand(i);
                if(nextCommand.equals("F")){
                    field.removeCar(car.getXPosition(),car.getYPosition());
                }
                
                car.runCommande(nextCommand);
                if(car.getXPosition() < 0 || car.getYPosition() < 0 || car.getXPosition() == field.xLength || car.getYPosition() == field.yLength ) {
                    System.out.println("Car "+car.getName()+" is going out of the field");
                    simulaionRestart(); 
                    return;                
                } 
                else if(nextCommand.equals("F") && field.getCar(car.getXPosition(),car.getYPosition()) == null)
                    field.addCar(car.getXPosition(),car.getYPosition(),car.getName());
                else if(nextCommand.equals("F")) {
                    System.out.println(car.getName()+", collides with "+field.getCar(car.getXPosition(),car.getYPosition())+" at ("+car.getXPosition()+","+car.getYPosition()+") at step "+(i+1));
                    System.out.println(field.getCar(car.getXPosition(),car.getYPosition())+", collides with "+car.getName()+" at ("+car.getXPosition()+","+car.getYPosition()+") at step "+(i+1));
                    simulaionRestart();
                    return;
                }
                
            }
            j++;
          }
          
          i++;
         }
        displayCars(true);
        simulaionRestart();
        
    }

    public void runSingleCarSimulation(){
        int i =0;
        Car car = cars.get(0);
        while (i < commandLength) {
            car.runCommande(car.getNextCommand(i));
            if(car.getXPosition() < 0 || car.getYPosition() < 0 || car.getXPosition() == field.xLength || car.getYPosition() == field.yLength ) {
                System.out.println("Car "+car.getName()+" is going out of the field");
                simulaionRestart(); 
                return;   
            }
            i++;
        }
        displayCars(true);
        simulaionRestart();
    }

    
    
    public void displayCars(boolean isResult){ 
        if(!cars.isEmpty())
        {
            int i =0;
            if(!isResult)
                System.out.println("Your current list of cars are:");
          
            while (i < cars.size()) {
                if(!isResult)
                    System.out.println("- "+cars.get(i).getName()+", ("+cars.get(i).getXPosition()+","+cars.get(i).getYPosition()+") "+cars.get(i).getDirection()+", "+String.join(",",cars.get(i).getCommands()));
                else
                    System.out.println("- "+cars.get(i).getName()+", ("+cars.get(i).getXPosition()+","+cars.get(i).getYPosition()+") "+cars.get(i).getDirection());
                i++;
            }
        }
    }
    
     public void simulaionRestart(){
       
        try{
            
            System.out.println("");
            System.out.println("Chose from the follwoing option");
            System.out.println("[1] To restart ");
            System.out.println("[2] To exit");
            
            String option = reader.readLine();
            switch (option) {
                case ADD -> startSimulation();
                case SIIMULATION -> System.exit(0);
            } 
        
        }catch(IOException e){
            System.out.println("wrong data entered");
            System.out.println(e.getMessage());
        }
    }
}
