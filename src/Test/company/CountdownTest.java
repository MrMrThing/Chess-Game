package company;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {
    @Test
    void CountdownTest() {
        Countdown testCoundtown = new Countdown();
        // find out if the milliseconds is eqeal the countdowns elapsedTime.
        assertEquals(890000, testCoundtown.elapsedTime);
    }
        @Test
        void CountdownTest1() {
            Countdown testCoundtown=new Countdown();
            assertNotNull(testCoundtown.counterLabel);
            assertNotNull(testCoundtown.counterLabel2);
            assertNotNull(testCoundtown.scoreCounter);
            assertNotNull(testCoundtown.scoreCounter2);
            assertNotNull(testCoundtown.eatScore);
        }
}