package company;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    Random m_randomMove;
    boolean m_color, m_turn;

    public AI(){
        this.m_randomMove = new Random();
        this.m_turn = false;
    }

    //This method chooses a random piece to move and moves it
    public void randomMove(Game g) {

        int pieceChosen = this.m_randomMove.nextInt(16); //chooses a number between 0 and 15
        Random randMove = new Random();
        int moveID;

        ArrayList<Piece> pieces = g.getPieces(); //we get the pieces from the game
        ArrayList<Piece> AIPieces = new ArrayList<>();

        //We get all the pieces our com.company.AI can move and store them apart from the others
        for (Piece piece : pieces) {
            if (piece.color = this.m_color) {
                AIPieces.add(piece);
            }
        }


        switch (pieceChosen){
            //cases 0 to 7 are pawns
            case 0:
                AIPieces.get(0).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                moveID = randMove.nextInt(AIPieces.get(0).possiblePositions.size());

                //send moveID to Board

                break;
            case 1:
                AIPieces.get(1).UpdatePossiblePositions(g); //we update the moves the selected pawn can do

                moveID = randMove.nextInt(AIPieces.get(1).possiblePositions.size());

                //send moveID to Board
                break;
            case 2:
                AIPieces.get(2).UpdatePossiblePositions(g); //we update the moves the selected pawn can do
                moveID = randMove.nextInt(AIPieces.get(2).possiblePositions.size());

                //send moveID to Board
                break;
            case 3:
                AIPieces.get(3).UpdatePossiblePositions(g); //we update the moves the selected pawn can do
                moveID = randMove.nextInt(AIPieces.get(3).possiblePositions.size());

                //send moveID to Board
                break;
            case 4:
                AIPieces.get(4).UpdatePossiblePositions(g); //we update the moves the selected pawn can do
                moveID = randMove.nextInt(AIPieces.get(4).possiblePositions.size());

                //send moveID to Board
                break;
            case 5:
                AIPieces.get(5).UpdatePossiblePositions(g); //we update the moves the selected pawn can do
                moveID = randMove.nextInt(AIPieces.get(5).possiblePositions.size());

                //send moveID to Board
                break;
            case 6:
                AIPieces.get(6).UpdatePossiblePositions(g); //we update the moves the selected pawn can do
                moveID = randMove.nextInt(AIPieces.get(6).possiblePositions.size());

                //send moveID to Board
                break;
            case 7:
                AIPieces.get(7).UpdatePossiblePositions(g); //we update the moves the selected pawn can do
                moveID = randMove.nextInt(AIPieces.get(7).possiblePositions.size());

                //send moveID to Board
                break;

            // 8 & 9 are knights
            case 8:
                AIPieces.get(8).UpdatePossiblePositions(g); //we update the moves the selected knight can do
                moveID = randMove.nextInt(AIPieces.get(8).possiblePositions.size());

                //send moveID to Board
                break;
            case 9:
                AIPieces.get(9).UpdatePossiblePositions(g); //we update the moves the selected knight can do
                moveID = randMove.nextInt(AIPieces.get(9).possiblePositions.size());

                //send moveID to Board
                break;

            //10 & 11 are bishops
            case 10:
                AIPieces.get(10).UpdatePossiblePositions(g); //we update the moves the selected bishop can do
                moveID = randMove.nextInt(AIPieces.get(10).possiblePositions.size());

                //send moveID to Board
                break;
            case 11:
                AIPieces.get(11).UpdatePossiblePositions(g); //we update the moves the selected bishop can do
                moveID = randMove.nextInt(AIPieces.get(11).possiblePositions.size());

                //send moveID to Board
                break;

            //rooks
            case 12:
                AIPieces.get(12).UpdatePossiblePositions(g); //we update the moves the selected rook can do
                moveID = randMove.nextInt(AIPieces.get(12).possiblePositions.size());

                //send moveID to Board
                break;
            case 13:
                AIPieces.get(13).UpdatePossiblePositions(g); //we update the moves the selected rook can do
                moveID = randMove.nextInt(AIPieces.get(13).possiblePositions.size());

                //send moveID to Board
                break;

            //Queen
            case 14:
                AIPieces.get(14).UpdatePossiblePositions(g); //we update the moves the queen can do
                moveID = randMove.nextInt(AIPieces.get(14).possiblePositions.size());

                //send moveID to Board
                break;

            //King
            case 15:
                AIPieces.get(15).UpdatePossiblePositions(g); //we update the moves the king can do
                moveID = randMove.nextInt(AIPieces.get(15).possiblePositions.size());

                //send moveID to Board
                break;
            default:
                System.out.println("Error in the random choice of movement from the com.company.AI");
                break;
        }
    }
}
