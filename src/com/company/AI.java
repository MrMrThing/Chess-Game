package com.company;
import java.util.ArrayList;
import java.util.Random;

public class AI {
    Random m_randomMove;
    //ArrayList<Piece> m_AIPieces;
    boolean m_color, m_turn;

    public AI(){
        this.m_randomMove = new Random();
        //this.m_AIPieces = new ArrayList<>();
        this.m_turn = false;
    }

    //This method chooses a random piece to move and moves it
    public void randomMove(Game g) {

        int moveID = this.m_randomMove.nextInt(16); //chooses a number between 0 and 15

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces from the game
        ArrayList<Piece> AIPieces = new ArrayList<>();

        //We get all the pieces our AI can move and store them apart from the others
        for (Piece piece : pieces) {
            if (piece.color = this.m_color) {
                AIPieces.add(piece);
            }
        }


        switch (moveID){
            //cases 0 to 7 are pawns
            case 0:
                AIPieces.get(0).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 1:
                AIPieces.get(1).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 2:
                AIPieces.get(2).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 3:
                AIPieces.get(3).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 4:
                AIPieces.get(4).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 5:
                AIPieces.get(5).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 6:
                AIPieces.get(6).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;
            case 7:
                AIPieces.get(7).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                break;

            // 8 & 9 are knights
            case 8:
                AIPieces.get(8).UpdatePossiblePositions(g); //we update the moves the selected knight can do

                break;
            case 9:
                AIPieces.get(9).UpdatePossiblePositions(g); //we update the moves the selected knight can do

                break;

            //10 & 11 are bishops
            case 10:
                AIPieces.get(10).UpdatePossiblePositions(g); //we update the moves the selected bishop can do

                break;
            case 11:
                AIPieces.get(11).UpdatePossiblePositions(g); //we update the moves the selected bishop can do

                break;

            //rooks
            case 12:
                AIPieces.get(12).UpdatePossiblePositions(g); //we update the moves the selected rook can do

                break;
            case 13:
                AIPieces.get(13).UpdatePossiblePositions(g); //we update the moves the selected rook can do

                break;

            //Queen
            case 14:
                AIPieces.get(14).UpdatePossiblePositions(g); //we update the moves the queen can do

                break;

            //King
            case 15:
                AIPieces.get(15).UpdatePossiblePositions(g); //we update the moves the king can do

                break;
            default:
                System.out.println("Error in the random choice of movement from the AI");
                break;
        }
    }




}
