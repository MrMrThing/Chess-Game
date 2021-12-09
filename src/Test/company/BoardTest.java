package company;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void paint() {
        Piece testPiece = new Rook(new Point(0,0), false);
        // See if the rook is not null, since it's not null. it's in the game.
        assertNotNull(testPiece.position);
    }

    @Test
    void paint1() {
        Piece testPiece = new Knight(new Point(0, 0), true);
        // See if the knight is not null, since it's not null. it's in the game.
        assertNotNull(testPiece.position);
    }
}
