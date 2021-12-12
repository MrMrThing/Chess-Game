package company;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void setPosition() {
        // check if the pieces in chess is not null, since it's not null. it's in the game.
        Piece testPiece = new Rook(new Point(0,0), false);
        assertNotNull(testPiece.position);
        // check the pieces value
        assertEquals(5,testPiece.value);

        Piece testPiece1 = new Knight(new Point(0,0), false);
        assertNotNull(testPiece1.position);
        assertEquals(3,testPiece1.value);

        Piece testPiece2 = new King(new Point(0,0), false);
        assertNotNull(testPiece2.position);
        double value = Double.POSITIVE_INFINITY;
        assertEquals(value,testPiece2.value);

        Piece testPiece3 = new Queen(new Point(0,0), false);
        assertNotNull(testPiece3.position);
        assertEquals(9,testPiece3.value);

        Piece testPiece4 = new Bishop(new Point(0,0), false);
        assertNotNull(testPiece4.position);
        assertEquals(3,testPiece4.value);

        Piece testPiece5 = new Pawn(new Point(0,0), false);
        assertNotNull(testPiece5.position);
        assertEquals(1,testPiece5.value);
    }
}