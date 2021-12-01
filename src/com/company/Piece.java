package com.company;
import java.awt.*;
import java.util.ArrayList;

public abstract class Piece{
    
    Point position;
    double value;
    Boolean color;
    String pieceName;
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

    abstract void loadBasicPossiblePositions();

    abstract void UpdatePossiblePositions(Game g); //the method where we will code the movements of each different piece

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

}

class Pawn extends Piece {

    boolean first_move ;

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

        if (g.player.m_turn) { //if player is playing
            //if it's the first move it can go two squares forward
            if(this.first_move){
                this.possiblePositions.add(new Point(this.position.x, this.position.y + 2));
            }

            //we add the position right in front of our pawn
            this.possiblePositions.add(new Point(this.position.x, this.position.y + 1));


            //We check for all pieces
            for (Piece p : pieces) {
                    //if there is a piece right in front of the pawn, this position isn't available to the pawn anymore
                    if (this.position.getX() == p.getPositionX() && this.position.getY() + 1 == p.getPositionY()) {
                        this.possiblePositions.remove(p.getPosition());

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
        }


        if (g.ai.m_turn) { //if the com.company.AI is playing

            if(this.first_move){ //first move: can go down two
                this.possiblePositions.add(new Point(this.position.x, this.position.y - 2));
            }
            //we add the position right in front of our pawn
            this.possiblePositions.add(new Point(this.position.x, this.position.y - 1));


            //We check for all pieces
            for (Piece p : pieces) {
                //if there is a piece right in front of the pawn, this position isn't available to the pawn anymore
                if (this.position.getX() == p.getPositionX() && this.position.getY() - 1 == p.getPositionY()) {
                    this.possiblePositions.remove(p.getPosition());

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
        }
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
                //x+2 et y-1
                if (this.position.getX() + 2 == i && this.position.getY() - 1 == j) {
                    this.possiblePositions.add(new Point(i, j));

                }
            }
        }
    }

    //The Knight can jump over pieces, so there isn't much to code
    void UpdatePossiblePositions(Game g) {
        this.loadBasicPossiblePositions(); //we start by getting the positions our piece can take in this move

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
            this.pieceName = "Bishop";

            this.loadBasicPossiblePositions();

        }

        //attempt with maria's advice, in a world where we don't use loadPossiblePieces before calling this
        void UpdatePossiblePositions(Game g){
            ArrayList<Piece> pieces = g.getPieces(); //we get the pieces from the Game

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops for the upper right diagonal
                //we go through all positions between our selected bishop and the end of the board
                for(int i = this.position.x; i < 8; i++){
                    for(int j = this.position.y; j < 8; j++){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                            break; //we get out of this loop because this position isn't available

                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop
                        }
                    }
                }
            }

            for(int a = 0; a < pieces.size(); a++){
                //loops for the down right diagonal
                for(int i = this.position.x; i < 8; i++){
                    for(int j = this.position.y; j == 0; j--){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in this position
                            break; //we get out of this loop
                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones
                        }
                    }
                }
            }

            for(int a = 0; a < pieces.size(); a++){
                //loops for the down left diagonal
                for(int i = this.position.x; i == 0; i--){
                    for(int j = this.position.y; j < 8; j++){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in this position
                            break; //we get out of this loop
                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones
                        }
                    }
                }
            }

            for(int a = 0; a < pieces.size(); a++){
                //loops for the upper left diagonal
                for(int i = this.position.x; i == 0; i--){
                    for(int j = this.position.y; j == 0; j--){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in this position
                            break; //we get out of this loop
                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones
                        }
                    }
                }
            }
        }

    //So we don't have to include the loop each time
        void loadBasicPossiblePositions() {
            //for every position on the board
           /*
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //If the position x=i and y=j is diagonal to the current position
                    if (Math.abs(this.position.getX() - i) ==
                            Math.abs(this.position.getY() - j)) {
                        //We add the position to the array of possible positions
                        this.possiblePositions.add(new Point(i, j));
                    }
                }
            }*/
        }

        /*
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
        }*/
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

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops for the way up (only y changes)
                //we go through all positions between our selected rook and the end of the board
                for(int j = this.position.y; j < 8; j++){
                        Point pos = new Point(this.position.x, j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our rook
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                            break; //we get out of this loop because this position isn't available

                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                        }
                }
            }

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops for the way down (only y changes)
                //we go through all positions between our selected rook and the end of the board
                for(int j = this.position.y; j == 0; j--){
                    Point pos = new Point(this.position.x, j);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops to go to the left (only x changes)
                //we go through all positions between our selected rook and the end of the board
                for(int i = this.position.x; i == 0; i--){
                    Point pos = new Point(i, this.position.y);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops to go to the right (only x changes)
                //we go through all positions between our selected rook and the end of the board
                for(int i = this.position.x; i < 8; i++){
                    Point pos = new Point(i, this.position.y);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }
        }

        @Override
        void loadBasicPossiblePositions() {
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

        /*void move(Game g) {

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
        }*/
    }

    class Queen extends Piece {

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

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops for the way up (only y changes)
                //we go through all positions between our selected rook and the end of the board
                for(int j = this.position.y; j < 8; j++){
                    Point pos = new Point(this.position.x, j);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops for the way down (only y changes)
                //we go through all positions between our selected rook and the end of the board
                for(int j = this.position.y; j == 0; j--){
                    Point pos = new Point(this.position.x, j);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops to go to the left (only x changes)
                //we go through all positions between our selected rook and the end of the board
                for(int i = this.position.x; i == 0; i--){
                    Point pos = new Point(i, this.position.y);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }

            ///-----Move like rook -----///

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops to go to the right (only x changes)
                //we go through all positions between our selected rook and the end of the board
                for(int i = this.position.x; i < 8; i++){
                    Point pos = new Point(i, this.position.y);

                    //In the case where there is a piece in this position, we check its color
                    //if it is different from our rook
                    if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook

                    } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                        break; //we get out of this loop because this position isn't available

                    } else{
                        this.possiblePositions.add(pos); //we add this position to the possible ones for our rook
                    }
                }
            }

            ///-----Move like rook -----///

            //For every piece existing on the board
            for(int a = 0; a < pieces.size(); a++){
                //loops for the upper right diagonal
                //we go through all positions between our selected bishop and the end of the board
                for(int i = this.position.x; i < 8; i++){
                    for(int j = this.position.y; j < 8; j++){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in the current position
                            break; //we get out of this loop because this position isn't available

                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop
                        }
                    }
                }
            }

            for(int a = 0; a < pieces.size(); a++){
                //loops for the down right diagonal
                for(int i = this.position.x; i < 8; i++){
                    for(int j = this.position.y; j == 0; j--){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in this position
                            break; //we get out of this loop
                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones
                        }
                    }
                }
            }

            for(int a = 0; a < pieces.size(); a++){
                //loops for the down left diagonal
                for(int i = this.position.x; i == 0; i--){
                    for(int j = this.position.y; j < 8; j++){
                        Point pos = new Point(i,j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if(pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color){
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if(pos == pieces.get(a).getPosition()){ //if there is a piece in this position
                            break; //we get out of this loop
                        } else{
                            this.possiblePositions.add(pos); //we add this position to the possible ones
                        }
                    }
                }
            }

            for(int a = 0; a < pieces.size(); a++) {
                //loops for the upper left diagonal
                for (int i = this.position.x; i == 0; i--) {
                    for (int j = this.position.y; j == 0; j--) {
                        Point pos = new Point(i, j);

                        //In the case where there is a piece in this position, we check its color
                        //if it is different from our bishop
                        if (pos == pieces.get(a).getPosition() && pieces.get(a).color != this.color) {
                            this.possiblePositions.add(pos); //we add this position to the possible ones for our bishop

                        } else if (pos == pieces.get(a).getPosition()) { //if there is a piece in this position
                            break; //we get out of this loop
                        } else {
                            this.possiblePositions.add(pos); //we add this position to the possible ones
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


