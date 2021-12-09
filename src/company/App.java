package company;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        //We create our frame
        //We will use this same frame for everything
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        //We call the menu
        frame.getContentPane().add(new Menu(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

/*
        //Setting a new Point
        Point thing = new Point(2,5);

        //Creating a Knight
        Knight k1 = new Knight(new Point(3,7), false);

        Queen Q1 = new Queen(new Point (4,5), false);

        //Getting the knights X Position
        System.out.println(k1.getPositionX());

        //Printing the Position of the Knight Piece
        System.out.println(k1.getPosition());

        //Getting location of the Point
        System.out.println(thing.getLocation());

        //Just getting the Y position of the Piece
        System.out.println(k1.getPosition().getY());
        
        System.out.println(Q1.getPossiblePositions().length);

        //Use Equals when comparing 2 Point locations
        if(k1.getPosition().equals(thing.getLocation())){
            System.out.println("Hello");

        }else{
            System.out.println("Fuck you");
        }

        if(k1.getPositionX() == thing.getX() && k1.getPositionY() == thing.getY()){
            System.out.println("Hello");

        }else{
            System.out.println("Fuck you");
        }

        System.out.println(k1.getPossiblePositions());*/
    }


    //Method for checking if an Array contains an value
   /* public static boolean contains (Integer[] array, Integer x){

        boolean result = false;
    
        for(int i = 0; i < array.length; i++){
            if(array[i] == x){
                result = true;
                break;
            }
        }
    
        return result;
    
    }*/


}



