package company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game{

    boolean isOver; //determines when the game is over
    Player player2; //the one playing from the top of the board
    Player player; //the one playing from the bottom of the board

    public ArrayList<Piece> m_pieces = new ArrayList<>(); //all pieces on the board are stored here
    //when a piece is taken off the board, we just delete it from the arrayList

    //Creation of the game
    public Game(){

        this.isOver = false;

        //We initialize the players
        player=new Player();
        player2 = new Player();

        //Here the player chooses which color he wants to play
        System.out.println("Hello player1. Do you want to play white or black? Enter 1 for white and 2 for black.");

        boolean color = true; //By default the player 1 will play the white pieces
        int choice;

        Scanner input = new Scanner(System.in);
        choice = input.nextInt();

        //We set the player's colors according to the choice made by player1
        if(choice == 1){
            this.player.m_color = true;
            this.player2.m_color = false;
        }
        else if(choice == 2){
            color = false;
            this.player.m_color = false;
            this.player2.m_color = true;
        }

        //--- We place every piece on their starting spot ---//

        //Starting with all player 1 pieces in their value order, then same for player 2
        for(int i = 0; i < 8; i++){ //player 1 pawns
            this.m_pieces.add(new Pawn(new Point(i,6), color));
        }

        //player 1 Knights and bishops
        this.m_pieces.add(new Knight(new Point(1,7), color));
        this.m_pieces.add(new Knight(new Point(6,7), color));
        this.m_pieces.add(new Bishop(new Point(2,7), color));
        this.m_pieces.add(new Bishop(new Point(5,7), color));
        //player 1 rooks
        this.m_pieces.add(new Rook(new Point(0,7), color));
        this.m_pieces.add(new Rook(new Point(7,7), color));
        this.m_pieces.add(new Queen(new Point(4,7), color)); //player 1 Queen
        this.m_pieces.add(new King(new Point(3,7), color)); //player 1 King


        //Same code for player 2 at the other side of the board with the color left
        for(int i = 0; i < 8; i++) { //player 2 pawns
            this.m_pieces.add(new Pawn(new Point(i, 1), !color));
        }

        //player 2 Knights and bishops
        this.m_pieces.add(new Knight(new Point(1,0), !color));
        this.m_pieces.add(new Knight(new Point(6,0), !color));
        this.m_pieces.add(new Bishop(new Point(2,0), !color));
        this.m_pieces.add(new Bishop(new Point(5,0), !color));
        //player 2 rooks
        this.m_pieces.add(new Rook(new Point(0,0), !color));
        this.m_pieces.add(new Rook(new Point(7,0), !color));
        this.m_pieces.add(new Queen(new Point(4,0), !color)); //player 2 Queen
        this.m_pieces.add(new King(new Point(3,0), !color)); //player 2 King
    }

    //This method is used to have access to the pieces still in the game
    public ArrayList<Piece> getPieces(){return this.m_pieces; }
}
