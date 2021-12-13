package company;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece{
    
    Point position;
    double value;
    Boolean color;
    String pieceName;
    Boolean first_move;
    Boolean beSelected; //whether the piece can be selected by the player or not

    ArrayList<Point> possiblePositions = new ArrayList<>(); //this is the array where we'll put the positions our piece
    //can take regardless of the positions already taken

    public Piece(Point pos, boolean color){

        this.position = pos;
        this.color = color;
        this.first_move = false; //for all pieces except pawn, first move is always false
        this.beSelected = true;
    }

    public boolean getColor(){return this.color; }

    public boolean getFirstMove(){return this.first_move; }

    public void setFirstMove(boolean fm){this.first_move = fm; }

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

    abstract void loadBasicPossiblePositions();

    abstract void UpdatePossiblePositions(Game g); //the method where we will code the movements of each different piece
    
    //This method takes a Point and an Array of Points
    //To check of the Single Point is inside the Array
    public boolean contains(Point pos, ArrayList<Point> points){
        boolean result = false;

        for(int i = 0; i < points.size(); i++){

            if(points.get(i).equals(pos)){
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void emptyPossiblePositions(){
        possiblePositions.clear();
    }

    //This method displays the infos of possiblePositions
    public void displayPossiblePositions(){
        System.out.println("----Here are the possible positions for " + pieceName + "---------\n");

        System.out.println("Number of positions in the array: " + this.possiblePositions.size());
        System.out.println("Here they are:\n");

        for(int i = 0; i < this.possiblePositions.size(); i++){
            System.out.println("x = " + this.possiblePositions.get(i).x +
                    " and y = " + this.possiblePositions.get(i).y + "\n");
        }
    }

}

class Pawn extends Piece {

    public Pawn(Point pos, boolean color) { //creation of a Pawn
        super(pos, color);
        this.value = 1;
        this.pieceName = "Pawn";
        this.first_move = true;
    }

    @Override
    void loadBasicPossiblePositions() { //the basic movements of a pawn

       /* for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if(this.first_move == true){
                    this.possiblePositions.add(new Point(this.position.x, this.position.y + 2));
                }
                //we can go one step up to the right
                if (this.position.getX() + 1 == i && this.position.getY() + 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                } else if (this.position.getX() - 1 == i && this.position.getY() + 1 == j) { //we can go one step up to the left
                    this.possiblePositions.add(new Point(i, j));

                }
            }
        }*/
    }

    //Here we take into account the state of the current game
    void UpdatePossiblePositions(Game g) {
        this.loadBasicPossiblePositions(); //we start by getting the positions our piece can take in this move
        Point currentPosition = this.getPosition(); //we keep our current position

        ArrayList<Point> takenPositions = g.getPositionsTaken(); //we get the position of all the pieces on the board

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

        if (!this.color && g.player2.m_color == this.color) { //if the pawn is black and player2 has black

            boolean nope = false;
            //we add the position right in front of our pawn
            this.possiblePositions.add(new Point(this.position.x, this.position.y + 1));


            //We check for all pieces
            for (Piece p : pieces) {
                    //if there is a piece right in front of the pawn, this position isn't available to the pawn anymore
                    if (this.position.getX() == p.getPositionX() && this.position.getY() + 1 == p.getPositionY()) {
                        this.possiblePositions.remove(p.getPosition());
                    }

                    //if a piece is two ahead we can't go
                if(this.position.getX() == p.getPositionX() && this.position.getY() + 2 == p.getPositionY()){
                    nope = true; //a piece is there, can't go further
                }

                    //if there is an enemy upper right, we can eat it
                if(p.color != this.color && this.position.x + 1 == p.getPositionX() && this.position.y + 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }

                //if there is an enemy upper left
                if(p.color != this.color && this.position.x - 1 == p.getPositionX() && this.position.y + 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }
            }
            if(this.first_move && this.possiblePositions.contains(new Point(this.position.x, this.position.y+1)) && !nope){ //if it's the first move it can go two squares forward
                this.possiblePositions.add(new Point(this.position.x, this.position.y + 2));
            }
        }

        if (this.color && g.player2.m_color == this.color) { //if the pawn is white and player2 has it

            boolean nope = false;
            //we add the position right in front of our pawn
            this.possiblePositions.add(new Point(this.position.x, this.position.y + 1));


            //We check for all pieces
            for (Piece p : pieces) {
                //if there is a piece right in front of the pawn, this position isn't available to the pawn anymore
                if (this.position.getX() == p.getPositionX() && this.position.getY() + 1 == p.getPositionY()) {
                    this.possiblePositions.remove(p.getPosition());
                    nope = true; //a piece is there, can't go further

                }

                //if a piece is two ahead we can't go
                if(this.position.getX() == p.getPositionX() && this.position.getY() + 2 == p.getPositionY()){
                    nope = true; //a piece is there, can't go further
                }

                //if there is an enemy upper right, we can eat it
                if(p.color != this.color && this.position.x + 1 == p.getPositionX() && this.position.y + 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }

                //if there is an enemy upper left
                if(p.color != this.color && this.position.x - 1 == p.getPositionX() && this.position.y + 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }
            }
            if(this.first_move && this.possiblePositions.contains(new Point(this.position.x, this.position.y+1)) && !nope){ //if it's the first move it can go two squares forward
                this.possiblePositions.add(new Point(this.position.x, this.position.y + 2));
            }
        }


        if (this.color && g.player.m_color == this.color) { //if the pawn is white and played by player1

            boolean nope = false;
            //we add the position right in front of our pawn
            this.possiblePositions.add(new Point(this.position.x, this.position.y - 1));


            //We check for all pieces
            for (Piece p : pieces) {
                //if there is a piece right in front of the pawn, this position isn't available to the pawn anymore
                if (this.position.getX() == p.getPositionX() && this.position.getY() - 1 == p.getPositionY()) {
                    this.possiblePositions.remove(p.getPosition());
                    nope = true; //a piece is there, can't go further

                }
                //if a piece is two ahead we can't go
                if(this.position.getX() == p.getPositionX() && this.position.getY() - 2 == p.getPositionY()){
                    nope = true; //a piece is there, can't go further
                }

                //if there is an enemy down-right, we can eat it
                if(p.color != this.color && this.position.x + 1 == p.getPositionX() && this.position.y - 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }

                //if there is an enemy down-left
                if(p.color != this.color && this.position.x - 1 == p.getPositionX() && this.position.y - 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }
            }
            if(this.first_move&& this.possiblePositions.contains(new Point(this.position.x, this.position.y-1)) && !nope){ //first move: can go up two
                this.possiblePositions.add(new Point(this.position.x, this.position.y - 2));
            }
        }

        if (!this.color && g.player.m_color == this.color) { //if the pawn is black and played by player1

            boolean nope = false;
            //we add the position right in front of our pawn
            this.possiblePositions.add(new Point(this.position.x, this.position.y - 1));


            //We check for all pieces
            for (Piece p : pieces) {
                //if there is a piece right in front of the pawn, this position isn't available to the pawn anymore
                if (this.position.getX() == p.getPositionX() && this.position.getY() - 1 == p.getPositionY()) {
                    this.possiblePositions.remove(p.getPosition());
                    nope = true; //a piece is there, can't go further

                }

                //if a piece is two ahead we can't go
                if(this.position.getX() == p.getPositionX() && this.position.getY() - 2 == p.getPositionY()){
                    nope = true; //a piece is there, can't go further
                }

                //if there is an enemy down-right, we can eat it
                if(p.color != this.color && this.position.x + 1 == p.getPositionX() && this.position.y - 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }

                //if there is an enemy down-left
                if(p.color != this.color && this.position.x - 1 == p.getPositionX() && this.position.y - 1 == p.getPositionY()){
                    this.possiblePositions.add(p.getPosition());
                }
            }
            if(this.first_move&& this.possiblePositions.contains(new Point(this.position.x, this.position.y-1)) && !nope){ //first move: can go up two
                this.possiblePositions.add(new Point(this.position.x, this.position.y - 2));
            }
        }
        this.displayPossiblePositions();
    }
}

class Knight extends Piece {

    public Knight(Point pos, boolean color) {
        super(pos, color);
        this.value = 3;
        this.pieceName = "Knight";
    }


    @Override
    void loadBasicPossiblePositions() { //all the positions the knight can originally go to
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
                //x-2 et y-1
                if (this.position.getX() - 2 == i && this.position.getY() - 1 == j) {
                    this.possiblePositions.add(new Point(i, j));
                }
            }
        }
    }

    //This method takes the basic possible positions and removes the one where an ally is
    void UpdatePossiblePositions(Game g) {
        this.loadBasicPossiblePositions(); //we start by getting the positions our piece can take in this move

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

        //If there is a same color piece on one of the possible positions, it's not a possible one anymore
        for (Piece p : pieces) {
            //if there is a piece at one of the possible positions
            if (this.possiblePositions.contains(p.getPosition())) {

                //if it's the same color
                if (p.getColor() == this.color) {
                    //System.out.println("We are removing this position: " + p.getPositionX() + ", " + p.getPositionY() + "\n");
                    this.possiblePositions.remove(p.getPosition()); //we remove that position from the possibilities
                }
            }
        }
    }
}

class Bishop extends Piece {

        public Bishop(Point pos, boolean color) {
            super(pos, color);
            this.value = 3;
            this.pieceName = "Bishop";

            this.loadBasicPossiblePositions();

        }

        //attempt with maria's advice, in a world where we don't use loadPossiblePieces before calling this
        void UpdatePossiblePositions(Game g){
            ArrayList<Piece> pieces = g.getPieces(); //we get the pieces from the Game

            ArrayList<Point> positionsTaken = new ArrayList<>();

            //We get all the positions taken by pieces
            for(Piece p : pieces){
                positionsTaken.add(p.getPosition());
            }

            //--- Bishop going upper right diagonal ---//

            //We start with the coordinates of the first square in the direction we are aiming for
            int urd_x = this.position.x+1;
            int urd_y = this.position.y-1;

            do{
                Point pos = new Point(urd_x, urd_y);

                if (positionsTaken.contains(pos)) { //If that position is taken
                    for (Piece p : pieces) { //we go through the pieces
                        if(p.color != this.color){ //if it's an enemy

                            if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                            }
                        }
                    }
                    //we get out of the loop when we encounter a piece bc we can't jump over it
                    urd_x = 7;
                    urd_y = 0;

                } else {
                    this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
                }

                urd_x++; //we increase x
                urd_y--; //we decrease y
            }while(urd_x <= 7 && urd_y >= 0);

            //--- Bishop going down right diagonal ---//

            //We start with the coordinates of the first square in the direction we are aiming for
            int drd_x = this.position.x+1;
            int drd_y = this.position.y+1;

            do{
                Point pos = new Point(drd_x, drd_y);

                if (positionsTaken.contains(pos)) { //If that position is taken
                    for (Piece p : pieces) { //we go through the pieces
                        if(p.color != this.color){ //if it's an enemy

                            if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                            }
                        }
                    }
                    //we get out of the loop when we encounter a piece bc we can't jump over it
                    drd_x = 7;
                    drd_y = 7;

                } else {
                    this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
                }

                drd_y++; //we increase x
                drd_x++; //we decrease y
            }while(drd_y <= 7 && drd_x <= 7);

            //--- Bishop going down left diagonal ---//

            //We start with the coordinates of the first square in the direction we are aiming for
            int dld_x = this.position.x-1;
            int dld_y = this.position.y+1;

            do{
                Point pos = new Point(dld_x, dld_y);

                if (positionsTaken.contains(pos)) { //If that position is taken
                    for (Piece p : pieces) { //we go through the pieces
                        if(p.color != this.color){ //if it's an enemy

                            if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                            }
                        }
                    }
                    //we get out of the loop when we encounter a piece bc we can't jump over it
                    dld_x = 0;
                    dld_y = 7;

                } else {
                    this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
                }

                dld_y++; //we increase x
                dld_x--; //we decrease y
            }while(dld_y <= 7 && dld_x >= 0);

            //--- Bishop going upper left diagonal ---//

            //We start with the coordinates of the first square in the direction we are aiming for
            int uld_x = this.position.x-1;
            int uld_y = this.position.y-1;

            do{
                Point pos = new Point(uld_x, uld_y);

                if (positionsTaken.contains(pos)) { //If that position is taken
                    for (Piece p : pieces) { //we go through the pieces
                        if(p.color != this.color){ //if it's an enemy

                            if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                            }
                        }
                    }
                    //we get out of the loop when we encounter a piece bc we can't jump over it
                    uld_x = 0;
                    uld_y = 0;

                } else {
                    this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
                }

                uld_y--; //we increase x
                uld_x--; //we decrease y
            }while(uld_y >= 0 && uld_x >= 0);


            this.displayPossiblePositions(); //testing
        }

    //So we don't have to include the loop each time
        void loadBasicPossiblePositions() {}
    }


class Rook extends Piece {

    public Rook(Point pos, boolean color) {
        super(pos, color);
        this.value = 5;
        this.pieceName = "Rook";
    }

    //attempt with maria's advice, in a world where we don't use loadPossiblePieces before calling this
    void UpdatePossiblePositions(Game g){
        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces from the Game

        //ArrayList<Point> positionsTaken = g.getPositionsTaken(); ///we never update m_positionsTaken so useless

        ArrayList<Point> positionsTaken = new ArrayList<>();

        //We get all the positions taken by pieces
        for(Piece p : pieces){
            positionsTaken.add(p.getPosition());
        }

        //--- Rook going downwards (y increases) ---//

        //For every piece existing on the board
            //loops for the way down (only y changes)
            //we go through all positions between our selected rook and the end of the board


        int downwardY = this.position.y + 1; //we start with the first square in the direction we want to go
        while(downwardY <= 7){
            Point pos = new Point(this.position.x, downwardY);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                downwardY= 8; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            downwardY++; //we decrease posy
        }


        //--- Rook going upwards (y decreases) ---//

        int upwardY = this.position.y -1; //we start with the first square in the direction we want to go
        while(upwardY >= 0){
            Point pos = new Point(this.position.x, upwardY);


            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                upwardY = 0; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            upwardY--; //we decrease posy
        }


        //--- Rook going to the left (x decreases) ---//

        int XtoLeft = this.position.x - 1; //we start with the first square in the direction we want to go
        while(XtoLeft >= 0){
            Point pos = new Point(XtoLeft, this.position.y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                XtoLeft = -1; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            XtoLeft--; //we decrease posy
        }

        //--- Rook going to the right (x increases) ---//

        int XtoRight = this.position.x + 1; //we start with the first square in the direction we want to go
        while(XtoRight <= 7){
            Point pos = new Point(XtoRight, this.position.y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                XtoRight = 8; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            XtoRight++; //we decrease posy
        }

        System.out.println("Let's check rook if it can move correctly\n");
        this.displayPossiblePositions(); //testing
    }

    @Override
    void loadBasicPossiblePositions() {
        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (i == this.position.getX()) { //if X stays the same
                    this.possiblePositions.add(new Point(i, j));

                } else if (j == this.position.getY()) { //if Y stays the same
                    this.possiblePositions.add(new Point(i, j));
                }
            }
        }*/
    }
}


class Queen extends Piece {
    //A queen has the same moves as a king, a bishop and a rook

    public Queen(Point pos, boolean color) {
        super(pos, color);
        this.value = 9;
        this.pieceName = "Queen";
    }


    @Override
    void loadBasicPossiblePositions() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

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

    void UpdatePossiblePositions(Game g) {
        //move like a king
        this.loadBasicPossiblePositions(); //we start by getting the positions our piece can take in this move

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces from Game

        ArrayList<Point> positionsTaken = new ArrayList<>();

        //We get all the positions taken by pieces
        for(Piece p : pieces){
            positionsTaken.add(p.getPosition());
        }


        ///-----Move like rook -----///

        //--- Rook going downwards (y increases) ---//

        //For every piece existing on the board
        //loops for the way down (only y changes)
        //we go through all positions between our selected rook and the end of the board


        int downwardY = this.position.y + 1; //we start with the first square in the direction we want to go
        while(downwardY <= 7){
            Point pos = new Point(this.position.x, downwardY);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                downwardY= 8; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            downwardY++; //we decrease posy
        }


        //--- Rook going upwards (y decreases) ---//

        int upwardY = this.position.y -1; //we start with the first square in the direction we want to go
        while(upwardY >= 0){
            Point pos = new Point(this.position.x, upwardY);


            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                upwardY = 0; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            upwardY--; //we decrease posy
        }


        //--- Rook going to the left (x decreases) ---//

        int XtoLeft = this.position.x - 1; //we start with the first square in the direction we want to go
        while(XtoLeft >= 0){
            Point pos = new Point(XtoLeft, this.position.y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                XtoLeft = -1; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            XtoLeft--; //we decrease posy
        }

        //--- Rook going to the right (x increases) ---//

        int XtoRight = this.position.x + 1; //we start with the first square in the direction we want to go
        while(XtoRight <= 7){
            Point pos = new Point(XtoRight, this.position.y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                XtoRight = 8; //we get out of the loop when we encounter a piece bc we can't jump over it
            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            XtoRight++; //we decrease posy
        }

        ///-----Move like bishop -----///

        //--- Bishop going upper right diagonal ---//

        //We start with the coordinates of the first square in the direction we are aiming for
        int urd_x = this.position.x+1;
        int urd_y = this.position.y-1;

        do{
            Point pos = new Point(urd_x, urd_y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                //we get out of the loop when we encounter a piece bc we can't jump over it
                urd_x = 7;
                urd_y = 0;

            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            urd_x++; //we increase x
            urd_y--; //we decrease y
        }while(urd_x <= 7 && urd_y >= 0);

        //--- Bishop going down right diagonal ---//

        //We start with the coordinates of the first square in the direction we are aiming for
        int drd_x = this.position.x+1;
        int drd_y = this.position.y+1;

        do{
            Point pos = new Point(drd_x, drd_y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                //we get out of the loop when we encounter a piece bc we can't jump over it
                drd_x = 7;
                drd_y = 7;

            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            drd_y++; //we increase x
            drd_x++; //we decrease y
        }while(drd_y <= 7 && drd_x <= 7);

        //--- Bishop going down left diagonal ---//

        //We start with the coordinates of the first square in the direction we are aiming for
        int dld_x = this.position.x-1;
        int dld_y = this.position.y+1;

        do{
            Point pos = new Point(dld_x, dld_y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                //we get out of the loop when we encounter a piece bc we can't jump over it
                dld_x = 0;
                dld_y = 7;

            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            dld_y++; //we increase x
            dld_x--; //we decrease y
        }while(dld_y <= 7 && dld_x >= 0);

        //--- Bishop going upper left diagonal ---//

        //We start with the coordinates of the first square in the direction we are aiming for
        int uld_x = this.position.x-1;
        int uld_y = this.position.y-1;

        do{
            Point pos = new Point(uld_x, uld_y);

            if (positionsTaken.contains(pos)) { //If that position is taken
                for (Piece p : pieces) { //we go through the pieces
                    if(p.color != this.color){ //if it's an enemy

                        if (p.getPosition().x == pos.x && p.getPosition().y == pos.y) { //if its position is the one we are looking for
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc diff color
                        }
                    }
                }
                //we get out of the loop when we encounter a piece bc we can't jump over it
                uld_x = 0;
                uld_y = 0;

            } else {
                this.possiblePositions.add(pos); //we add this position to the possible ones for our rook bc no piece is there
            }

            uld_y--; //we increase x
            uld_x--; //we decrease y
        }while(uld_y >= 0 && uld_x >= 0);


        this.displayPossiblePositions(); //testing
    }
}


class King extends Piece {

    public King(Point pos, boolean color) {
        super(pos, color);
        this.value = Double.POSITIVE_INFINITY; //the king's value is infinite
        this.pieceName = "King";
    }

    @Override
    void loadBasicPossiblePositions() { //could be optimised probably
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

    //This method updates the King's possibilities of movements thanks to the current state of the board
    void UpdatePossiblePositions(Game g) {
        this.loadBasicPossiblePositions(); //we start by getting the positions our piece can take in this move

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces

        //If there is a same color piece on one of the possible positions, it's not a possible one anymore
        for (Piece p : pieces) {
            //if there is a piece at one of the possible positions
            if (this.possiblePositions.contains(p.getPosition())) {

                //if it's the same color
                if (p.getColor() == this.color) {
                    //System.out.println("We are removing this position: " + p.getPositionX() + ", " + p.getPositionY() + "\n");
                    this.possiblePositions.remove(p.getPosition()); //we remove that position from the possibilities
                }
            }
        }

    }
}


