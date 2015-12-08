package cs121.studyparty.test;

import android.test.InstrumentationTestCase;

import cs121.studyparty.Room;

/**
 * Created by Bailey on 12/4/15.
 */
public class tests extends InstrumentationTestCase {

    public void test1() throws Exception {
        Room sampleRoom = new Room();
        int sampleNum = sampleRoom.getNumOccupants();
        int bad = 5;
        int good = 0;
        assertEquals(sampleNum, good);
        assertNotSame(sampleNum, bad);
    }

    public void test2() throws Exception {
        Room sampleRoom = new Room();
        sampleRoom.setOccupancy(true);
        boolean sampleOccupancy = sampleRoom.getOccupancy();
        int bad = 5;
        boolean good = true;
        assertEquals(sampleOccupancy, good);
        assertNotSame(sampleOccupancy, bad);
    }

}

