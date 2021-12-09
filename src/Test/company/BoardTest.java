package company;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void paint() {
        // check if the pieces in chess is not null, since it's not null. it's in the game.
        Piece testPiece = new Rook(new Point(0,0), false);
        assertNotNull(testPiece.position);

        Piece testPiece1 = new Knight(new Point(0,0), false);
        assertNotNull(testPiece1.position);

        Piece testPiece2 = new King(new Point(0,0), false);
        assertNotNull(testPiece2.position);

        Piece testPiece3 = new Queen(new Point(0,0), false);
        assertNotNull(testPiece3.position);

        Piece testPiece4 = new Bishop(new Point(0,0), false);
        assertNotNull(testPiece4.position);

        Piece testPiece5 = new Pawn(new Point(0,0), false);
        assertNotNull(testPiece5.position);
    }


    @Test
    void move() {

    }
}
