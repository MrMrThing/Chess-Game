package com.company;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece{
    
    Point position;
    double value;
    Boolean color;
    ArrayList<Point> possiblePositions = new ArrayList<>(); //this is the array where we'll put the positions our piece
    //can take regardless of the positions already taken

    public Piece(Point pos, boolean color){

        this.position = pos;
        this.color = color;

    }

    public boolean getColor(){return this.color; }


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

    public ArrayList<Point> getPossiblePositions(){ return this.possiblePositions; }

    abstract void loadPossiblePositions();

    abstract void move(Game g); //the method where we will code the movements of each different piece

    

}

class Pawn extends Piece {

    public Pawn(Point pos, boolean color) { //creation of a Pawn
        super(pos, color);
        this.value = 1;
    }

    @Override
    void loadPossiblePositions() { //the basic movements of a pawn

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //we can go one step up to the right
                if (this.position.getX() + 1 == i && this.position.getY() + 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                } else if (this.position.getX() - 1 == i && this.position.getY() + 1 == j) { //we can go one step up to the left
                    this.possiblePositions.add(new Point(i, j));

                }
            }
        }

    }

    //Here we take into account the state of the current game
    void move(Game g) {
        this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move
        Point currentPosition = this.getPosition(); //we keep our current position
        Point isItOccupiedWayUp = new Point((int) this.getPositionX(), (int) this.getPositionY() + 1); //is this occupied by the pieces coming down
        Point isItOccupiedWayDown = new Point((int) this.getPositionX(), (int) this.getPositionY() - 1); //is this occupied by the pieces coming up

        ArrayList<Point> takenPositions = g.getPositionsTaken(); //we get the position of all the pieces on the board

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

        if (g.player1.m_turn) { //if player one is playing
            //if there is an enemy on the square in front of the pawn
            if (takenPositions.contains(isItOccupiedWayUp)) {
                for (Piece p : pieces) {
                    if (p.getPosition() == isItOccupiedWayUp) { //we get the piece that can be eaten by the pawn
                        if (p.getColor() == !this.color) { //if it's the other color
                            //this position is available for the pawn to go to
                            this.possiblePositions.add(isItOccupiedWayUp);
                        }
                    }
                }
            }

            //case where a piece the same color makes the position unavailable to the pawn
            for (int i = 0; i < this.possiblePositions.size(); i++) {
                for (Piece p : pieces) {
                    //if there is a piece at one of the possible positions
                    if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {
                        //if it's the same color
                        if (p.getColor() == this.color) {
                            this.possiblePositions.remove(i); //we remove that position from the possibilities
                        }
                    }
                }
            }
        }

        if (g.player2.m_turn) { //if player 2 is playing
            //if there is an enemy on the square in front of the pawn
            if (takenPositions.contains(isItOccupiedWayDown)) {
                for (Piece p : pieces) {
                    if (p.getPosition() == isItOccupiedWayDown) { //we get the piece that can be eaten by the pawn
                        if (p.getColor() == !this.color) { //if it's the other color
                            //this position is available for the pawn to go to
                            this.possiblePositions.add(isItOccupiedWayDown);
                        }
                    }
                }
            }

            //a piece the same color makes the position unavailable to the pawn
            for (int i = 0; i < this.possiblePositions.size(); i++) {
                for (Piece p : pieces) {
                    //if there is a piece at one of the possible positions
                    if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {
                        //if it's the same color
                        if (p.getColor() == this.color) {
                            this.possiblePositions.remove(i); //we remove that position from the possibilities
                        }
                    }
                }
            }

            //When we click on a pawn, we show the positions available with possiblePositions array

            //When we click on the chosen position, the image of the pawn moves to said chosen position

        }
    }
}

class Knight extends Piece {

    public Knight(Point pos, boolean color) {
        super(pos, color);
        this.value = 3;
    }


    @Override
    void loadPossiblePositions() { //all the positions the knight can originally go to
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //x+2 et y+1
                if (this.position.getX() + 2 == i && this.position.getY() + 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x+2 et y-1
                if (this.position.getX() + 2 == i && this.position.getY() - 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x+1 et y+2
                if (this.position.getX() + 1 == i && this.position.getY() + 2 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x-1 et y+2
                if (this.position.getX() -1 == i && this.position.getY() + 2 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x+1 et y-2
                if (this.position.getX() + 1 == i && this.position.getY() -2 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x-1 et y-2
                if (this.position.getX() -1 == i && this.position.getY() -2 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x-2 et y+1
                if (this.position.getX() - 2 == i && this.position.getY() + 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
                //x+2 et y-1
                if (this.position.getX() + 2 == i && this.position.getY() - 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
            }
        }
    }

    //The Knight can jump over pieces, so there isn't much to code
    void move(Game g) {
        this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

        //If there is a same color piece on one of the possible positions, it's not a possible one anymore
        for (int i = 0; i < this.possiblePositions.size(); i++) {
            for (Piece p : pieces) {
                //if there is a piece at one of the possible positions
                if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {
                    //if it's the same color
                    if (p.getColor() == this.color) {
                        this.possiblePositions.remove(i); //we remove that position from the possibilities
                    }
                }
            }
        }
    }
}

class Bishop extends Piece {

        public Bishop(Point pos, boolean color) {
            super(pos, color);
            this.value = 3;

            this.loadPossiblePositions();

        }

        //So we don't have to include the loop each time
        void loadPossiblePositions() {
            //for every position on the board
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //If the position x=i and y=j is diagonal to the current position
                    if (Math.abs(this.position.getX() - i) ==
                            Math.abs(this.position.getY() - j)) {
                        //We add the position to the array of possible positions
                        this.possiblePositions.add(new Point(i, j));
                    }
                }
            }
        }

        //The bishop can only move in diagonals until it encounters another piece OR the end of the board
        void move(Game g) {
            this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move

            ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

            //If there is a same color piece on one of the possible positions, it's not a possible one anymore
            for (int i = 0; i < this.possiblePositions.size(); i++) {
                for (Piece p : pieces) {
                    //if there is a piece at one of the possible positions
                    if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {

                        //regardless of the color, we delete all positions that come after
                        for(int h = i+1; h <= this.possiblePositions.size(); h++){

                          //not sure about this if... mathematically it's fine but still some kind of doubt
                                if (Math.abs(p.getPositionX() - h) == Math.abs(p.getPositionY() - h)) {
                                    //We delete the position from the array of possible positions
                                    this.possiblePositions.remove(new Point(h, h));
                                }
                        }
                        //if it's the same color
                        if (p.getColor() == this.color) {
                            this.possiblePositions.remove(i); //we remove that position from the possibilities
                        }
                    }
                }
            }
        }
    }


    class Rook extends Piece {

        public Rook(Point pos, boolean color) {
            super(pos, color);
            this.value = 5;
        }


        @Override
        void loadPossiblePositions() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (i == this.position.getX()) { //if X stays the same
                        this.possiblePositions.add(new Point(i, j));

                    } else if (j == this.position.getY()) { //if Y stays the same
                        this.possiblePositions.add(new Point(i, j));
                    }
                }
            }
        }

        void move(Game g) {

            //if there is an obstacle we can't go further

            this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move

            ArrayList<Piece> pieces = g.getPieces(); //we get the pieces


            for (int i = 0; i < this.possiblePositions.size(); i++) {
                for (Piece p : pieces) {
                    //if there is a piece at one of the possible positions
                    if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {

                        //regardless of the color, we delete all positions that come after <-- not sure about this
                        for(int h = i+1; h <= this.possiblePositions.size(); h++){

                            //if the position h shares its x or y with the piece in i
                            if(p.getPositionX() + h == this.possiblePositions.get(h).getX() || p.getPositionY() + h == this.possiblePositions.get(h).getY()){
                                this.possiblePositions.remove(h);
                            }
                        }
                        //If there is a same color piece on one of the possible positions, it's not a possible one anymore
                        if (p.getColor() == this.color) {
                            this.possiblePositions.remove(i); //we remove that position from the possibilities
                        }
                    }
                }
            }
        }
    }

    class Queen extends Piece {

        public Queen(Point pos, boolean color) {
            super(pos, color);
            this.value = 9;
        }


        @Override
        void loadPossiblePositions() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    //She can to the movements of the rook
                    if (i == this.position.getX()) { //if X stays the same
                        this.possiblePositions.add(new Point(i, j));

                    } else if (j == this.position.getY()) { //if Y stays the same
                        this.possiblePositions.add(new Point(i, j));
                    }

                    //She can do the movements of the bishop
                    //If the position x=i and y=j is diagonal to the current position
                    if (Math.abs(this.position.getX() - i) ==
                            Math.abs(this.position.getY() - j)) {
                        //We add the position to the array of possible positions
                        this.possiblePositions.add(new Point(i, j));
                    }

                    //She can do the movements of the King
                    //go left or right by one
                    if (i < this.position.getX() + 2 && i > this.position.getX() - 2 && j == this.position.getY()) {
                        this.possiblePositions.add(new Point(i, j));

                        //go up or down by one
                    } else if (j < this.position.getY() + 2 && j > this.position.getY() - 2 && i == this.position.getX()) {
                        this.possiblePositions.add(new Point(i, j));

                        //right up corner
                    } else if (i == this.position.getX() + 1 && j == this.position.getY() + 1) {
                        this.possiblePositions.add(new Point(i, j));

                        //right down corner
                    } else if (i == this.position.getX() + 1 && j == this.position.getY() - 1) {
                        this.possiblePositions.add(new Point(i, j));

                        //left up corner
                    } else if (i == this.position.getX() - 1 && j == this.position.getY() + 1) {
                        this.possiblePositions.add(new Point(i, j));

                        //left down corner
                    } else if (i == this.position.getX() - 1 && j == this.position.getY() - 1) {
                        this.possiblePositions.add(new Point(i, j));
                    }
                }
            }
        }

        void move(Game g) {
            this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move

            ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

            //If there is a same color piece on one of the possible positions, it's not a possible one anymore
            for (int i = 0; i < this.possiblePositions.size(); i++) {
                for (Piece p : pieces) {
                    //if there is a piece at one of the possible positions
                    if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {

                        //regardless of the color, we need to remove all positions that come after this one




                        //if it's the same color, the current i position is not available either
                        if (p.getColor() == this.color) {
                            this.possiblePositions.remove(i); //we remove that position from the possibilities
                        }
                    }
                }
            }
        }
    }

    class King extends Piece {

        public King(Point pos, boolean color) {
            super(pos, color);
            this.value = Double.POSITIVE_INFINITY; //the king's value is infinite
        }

        @Override
        void loadPossiblePositions() { //could be optimised probably
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    //go left or right by one
                    if (i < this.position.getX() + 2 && i > this.position.getX() - 2 && j == this.position.getY()) {
                        this.possiblePositions.add(new Point(i, j));

                        //go up or down by one
                    } else if (j < this.position.getY() + 2 && j > this.position.getY() - 2 && i == this.position.getX()) {
                        this.possiblePositions.add(new Point(i, j));

                        //right up corner
                    } else if (i == this.position.getX() + 1 && j == this.position.getY() + 1) {
                        this.possiblePositions.add(new Point(i, j));

                        //right down corner
                    } else if (i == this.position.getX() + 1 && j == this.position.getY() - 1) {
                        this.possiblePositions.add(new Point(i, j));

                        //left up corner
                    } else if (i == this.position.getX() - 1 && j == this.position.getY() + 1) {
                        this.possiblePositions.add(new Point(i, j));

                        //left down corner
                    } else if (i == this.position.getX() - 1 && j == this.position.getY() - 1) {
                        this.possiblePositions.add(new Point(i, j));
                    }
                }
            }
        }

        void move(Game g) {
            this.loadPossiblePositions(); //we start by getting the positions our piece can take in this move

            ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

            //If there is a same color piece on one of the possible positions, it's not a possible one anymore
            for (int i = 0; i < this.possiblePositions.size(); i++) {
                for (Piece p : pieces) {
                    //if there is a piece at one of the possible positions
                    if (p.getPosition() == this.possiblePositions.get(i).getLocation()) {

                        //if it's the same color
                        if (p.getColor() == this.color) {
                            this.possiblePositions.remove(i); //we remove that position from the possibilities
                        }
                    }
                }
            }
        }
    }


