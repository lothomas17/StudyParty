package cs121.studyparty.test;

import android.test.InstrumentationTestCase;

import java.util.List;

import cs121.studyparty.Room;
import cs121.studyparty.User;

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


    public void test3() throws Exception {
        Room sampleRoom = new Room();
        sampleRoom.setNumOccupants(5);
        int sampleOccupancy = sampleRoom.getNumOccupants();
        int good = 5;
        boolean bad = true;
        assertEquals(sampleOccupancy, good);
        assertNotSame(sampleOccupancy, bad);
    }


    public void test4() throws Exception {
        Room sampleRoom = new Room();
        sampleRoom.setName("TESTROOM");
        String good = "TESTROOM";
        String bad = null;
        String name = sampleRoom.getRoomName();
        assertEquals(name, good);
        assertNotSame(name, bad);
    }


    public void test5() throws Exception {
        Room sampleRoom = new Room();
        User sampleUser = new User();
        sampleUser.setID("TESTER");
        sampleRoom.addUsertoParty(sampleUser);

        int good = 0; // shouldn't actually update number of users
        int test = sampleRoom.getNumOccupants();
        boolean occupied = sampleRoom.getOccupancy();
        assertEquals(good, test);
        assertEquals(true, occupied);
        List<User> users = sampleRoom.getOccupants();
        int size = users.size();
        assertEquals(1, size);
        String ID = users.get(0).getID();
        assertEquals("TESTER", ID);
    }

}

