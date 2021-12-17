package company;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {
    @Test
    void CountdownTest() {
        Countdown testCoundtown = new Countdown();
        // find out if the milliseconds is eqeal the countdowns elapsedTime.
        assertEquals(900000, testCoundtown.elapsedTime);
    }
        @Test
        void CountdownTest1() {
            Countdown testCoundtown=new Countdown();
            // assert that following JLabels isn't null
            assertNotNull(testCoundtown.counterLabel);
            assertNotNull(testCoundtown.counterLabel2);
            assertNotNull(testCoundtown.scoreCounter);
            assertNotNull(testCoundtown.scoreCounter2);
            assertNotNull(testCoundtown.eatScore);
        }
}