package company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {
    // find out if the milliseconds is eqeal the countdowns elapsedTime.
    @Test
    void CountdownTest() {
        Countdown testCoundtown=new Countdown();
        assertEquals(890000, testCoundtown.elapsedTime);



    }
}