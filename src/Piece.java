

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece{
    
    Point position;
    double value;
    Boolean Color;
    ArrayList<Point> possiblePositions = new ArrayList<>(); //this is the array where we'll put the positions our piece
    //can take regardless of the positions already taken

    public Piece(Point pos, boolean color){

        this.position = pos;
        this.Color = color;

    }


    //Command to set a new position for the piece
    public void setPosition(Integer posX, Integer posY){
        this.position.setLocation(posX, posY);
    }

    //Command for getting the current position of a piece
    public Point getPosition(){
        return this.position.getLocation();
    }

    //Command for getting the X coordinate 
    public double getPositionX(){
        return this.position.getX();
    }

    //Command for getting the Y coordinate
    public double getPositionY(){
        return this.position.getY();
    }

    abstract void loadPossiblePositions();

    abstract void move(Game g); //the method where we will code the movements of each different piece

    

}

class Pawn extends Piece{

    public Pawn(Point pos, boolean color){ //creation of a Pawn
        super(pos, color);
        this.value = 1;
    }

    @Override
    void loadPossiblePositions() {

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //if we have one ahead on the right
                if(this.position.getX()+1 == i && this.position.getY()+1 == j){
                    this.possiblePositions.add(new Point(i,j));

                }else if(this.position.getX()-1 == i && this.position.getY()+1 == j){ //one ahead on the left
                    this.possiblePositions.add(new Point(i,j));

                }
            }
        }

    }

    void move(Game g){
        this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move
        Point currentPosition = this.getPosition(); //we keep our current position
        Point isItOccupiedWayUp = new Point((int) this.getPositionX(), (int) this.getPositionY()+1); //is this occupied by the pieces coming down
        Point isItOccupiedWayDown = new Point((int) this.getPositionX(), (int) this.getPositionY()-1); //is this occupied by the pieces coming up

        ArrayList<Point> takenPositions = g.getPositionsTaken(); //we get the position of all the pieces on the board
/*
        for(int i = 0; i < takenPositions.size(); i++){
            if(takenPositions.get(i) == isItOccupiedWayUp && takenPositions.get(i).){

            }
            if(takenPositions.get(i) == isItOccupiedWayDown){

            }
        }*/
        //if(g.m_positionsTaken)

        //depending on who's playing: y+1 or y=-1

        //if(piece one position ahead){ this.possiblePositionsForThisPiece.add(new Point(this.position.getX()+1, this.position.getY()+1)); }
        //need to wait for class Game to have the oponent's positions to know if we can go one ahead or not for the last case
        //in this case we eat the opponent's piece
    }
}

class Knight extends Piece{

    public Knight(Point pos, boolean color) {
        super(pos, color);
        this.value = 3;
        
    }


    @Override
    void loadPossiblePositions() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

            }
        }
    }

    void move(Game g){

    }
}

class Bishop extends Piece{

    public Bishop(Point pos, boolean color){
        super(pos, color);
        this.value = 3;

        this.loadPossiblePositions();

    }

    //So we don't have to include the loop eahc time
    void loadPossiblePositions(){
        //for every position on the board
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //If the position x=i and y=j is diagonal to the current position
                if( Math.abs(this.position.getX() - i) ==
                        Math.abs(this.position.getY() - j)){
                    //We add the position to the array of possible positions
                    this.possiblePositions.add(new Point(i,j));
                }
            }
        }
    }

    //The bishop can only move in diagonals until it encounters another piece OR the end of the board
    void move(Game g){
        boolean isMoveValid = false;
        Point destination = new Point(); //how do we get the position the player wants?

        //First we check if the position is possible for a bishop
        //If the destination is diagonal to the position
        if( Math.abs(this.position.getX() - destination.getX()) == Math.abs(this.position.getY() - destination.getY()) ){
            //first step is good

            //Step 2: is there a piece on the way?
            Point i = new Point((int)position.getX()+1, (int)position.getY()+1);
            do{
                //we check if i is an empty position


            }while(!isMoveValid);
            this.position.setLocation(destination); //we put the piece at its new position
        }
        //At the end of each move, we need to empty the array of possiblePositionsForThisPiece and fill it again with the new possibilities
        this.possiblePositions.clear(); //we empty it
        this.loadPossiblePositions();
    }
}



class Rook extends Piece{

    public Rook(Point pos, boolean color){
        super(pos, color);
        this.value = 5;
    }


    @Override
    void loadPossiblePositions() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(i == this.position.getX()){ //if X stays the same
                    this.possiblePositions.add(new Point(i,j));

                }else if(j == this.position.getY()){ //if Y stays the same
                    this.possiblePositions.add(new Point(i,j));
                }
            }
        }
    }

    void move(Game g){

    }
}

class Queen extends Piece{

    public Queen(Point pos, boolean color){
        super(pos, color);
        this.value = 9;
    }


    @Override
    void loadPossiblePositions() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                //She can to the movements of the rook
                if(i == this.position.getX()){ //if X stays the same
                    this.possiblePositions.add(new Point(i,j));

                }else if(j == this.position.getY()){ //if Y stays the same
                    this.possiblePositions.add(new Point(i,j));
                }

                //She can do the movements of the bishop
                //If the position x=i and y=j is diagonal to the current position
                if( Math.abs(this.position.getX() - i) ==
                        Math.abs(this.position.getY() - j)){
                    //We add the position to the array of possible positions
                    this.possiblePositions.add(new Point(i,j));
                }

                //She can do the movements of the King
                //go left or right by one
                if(i < this.position.getX()+2 && i > this.position.getX()-2 && j == this.position.getY()){
                    this.possiblePositions.add(new Point(i,j));

                    //go up or down by one
                }else if(j < this.position.getY()+2 && j > this.position.getY()-2 && i == this.position.getX()){
                    this.possiblePositions.add(new Point(i,j));

                    //right up corner
                } else if(i == this.position.getX()+1 && j == this.position.getY()+1){
                    this.possiblePositions.add(new Point(i,j));

                    //right down corner
                }else if(i == this.position.getX()+1 && j == this.position.getY()-1){
                    this.possiblePositions.add(new Point(i,j));

                    //left up corner
                }else if(i == this.position.getX()-1 && j == this.position.getY()+1){
                    this.possiblePositions.add(new Point(i,j));

                    //left down corner
                }else if(i == this.position.getX()-1 && j == this.position.getY()-1){
                    this.possiblePositions.add(new Point(i,j));
                }
            }
        }
    }

    void move(Game g){

    }
}

class King extends Piece{

    public King(Point pos, boolean color){
        super(pos, color);
        this.value = Double.POSITIVE_INFINITY; //the king's value is infinite
    }

    @Override
    void loadPossiblePositions() { //could be optimised probably
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                //go left or right by one
                if(i < this.position.getX()+2 && i > this.position.getX()-2 && j == this.position.getY()){
                    this.possiblePositions.add(new Point(i,j));

                    //go up or down by one
                }else if(j < this.position.getY()+2 && j > this.position.getY()-2 && i == this.position.getX()){
                    this.possiblePositions.add(new Point(i,j));

                    //right up corner
                } else if(i == this.position.getX()+1 && j == this.position.getY()+1){
                    this.possiblePositions.add(new Point(i,j));

                    //right down corner
                }else if(i == this.position.getX()+1 && j == this.position.getY()-1){
                    this.possiblePositions.add(new Point(i,j));

                    //left up corner
                }else if(i == this.position.getX()-1 && j == this.position.getY()+1){
                    this.possiblePositions.add(new Point(i,j));

                    //left down corner
                }else if(i == this.position.getX()-1 && j == this.position.getY()-1){
                    this.possiblePositions.add(new Point(i,j));
                }
            }
        }
    }

    void move(Game g){

    }
}
