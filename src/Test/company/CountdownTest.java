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
        assertEquals(900000, testCoundtown.elapsedTime1);
    }
        @Test
        void CountdownTest1() {
            Countdown testCoundtown=new Countdown();
            // assert that following JLabels isn't null
            assertNotNull(testCoundtown.counter1);
            assertNotNull(testCoundtown.counter2);
            assertNotNull(testCoundtown.scoreCounter1);
            assertNotNull(testCoundtown.scoreCounter2);
            assertNotNull(testCoundtown.eatScore);
        }
}