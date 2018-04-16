package cs2340.shelterbuzz;

/* Skeleton test file for ShelterManager class,
 * with Firebase instance protection using Mockito.
 *
 * Code is labeled as follows:
 *      FIREBASE: Code needed for Firebase simulation
 *      PRIVATE: Code needed to simulate private method calls
 *               e.g. if method being tested is not private, no need to use
 *      GENERIC: Generic JUnit code
 *
 *  You may need to replace certain parts to fit your particular test case
 */

/* GENERIC */
import org.junit.Test;
import org.junit.Before;

import cs2340.shelterbuzz.model.Age;
import cs2340.shelterbuzz.model.Gender;
import cs2340.shelterbuzz.model.Shelter;
import cs2340.shelterbuzz.model.ShelterManager; // replace with your class
/* END GENERIC */

/* FIREBASE */
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
/* END FIREBASE */

/* PRIVATE */
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* END PRIVATE */


/**
 * Tests checkIn, which is an instance method of ShelterManager
 */

/* FIREBASE */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
/* END FIREBASE */
public class YounTests {
    private ShelterManager manager;
    private List<Shelter> list;

    /* FIREBASE */
    @Mock
    private DatabaseReference mockedDatabaseReference;
	/* END FIREBASE */

    /* PRIVATE */
	/* END PRIVATE */
	private Shelter s1 = new Shelter(0, "My Sister's House", 264,
            "Women/Children", -84.41014, 33.78017,
            "921 Howell Mill Road, Atlanta, Georgia 30318",
            "Temporary, Emergency, Residential Recovery",
            "(404) 367-2465)");
	private Shelter s2 = new Shelter(1, "The Atlanta Day Center for Women & Children",
            140,
            "Women/Children", -84.408771, 33.784889,
            "655 Ethel Street, Atlanta, Georgia 30318",
            "Career Facilitation",
            "(404) 588-4007");
	private Shelter s3 = new Shelter(2, "The Shepherd's Inn", 450, "Men", -84.39265, 33.765162,
            "156 Mills Street, Atlanta, Georgia 30313",
            "Temporary, Residential Recovery",
            "(404) 367-2493)");
	private Shelter s4 = new Shelter(3, "Fuqua Hall", 92, "Men", -84.392273, 33.76515,
            "144 Mills Street, Atlanta, Georgia 30313",
            "Transitional housing",
            "(404) 367-2492)");
	private Shelter s5 = new Shelter(4, "Atlanta's Children Center", 40, "Families w/ Children under 5",
            -84.384433, 33.770949,
            "607 Peachtree Street NE Atlanta, GA 30308",
            "Children's programs, early childhood education",
            "(404) 892-3713)");
	private Shelter s6 = new Shelter(5, "Eden Village", 112, "Women/Children", -84.43023, 33.762316,
            "1300 Joseph E. Boone Blvd NW, Atlanta, GA 30314",
            "General recovery services",
            "(404)-874-2241");
	private Shelter s7 = new Shelter(6, "Our House", 76, "Families w/ newborns",
            -84.371706, 33.759138,
	        "173 Boulevard Northeast, Atlanta, GA 30312",
            "Families w/ Newborns, pre-K education",
            "(404) 522-6056");
    private Shelter s8 = new Shelter(7, "Covenant House Georgia", 80, "Childrens/Young adults",
            -84.437988, 33.78823,
            "1559 Johnson Road NW, Atlanta, GA 30318",
            "Crisis services/Career Preparation",
            "(404)-937-6957");
    private Shelter s9 = new Shelter(8, "Hope Atlanta", 22, "Anyone",
            -84.390429, 33.753594,
            "34 Peachtree Street NW, Suite 700, Atlanta, GA 30303",
            "Emergency shelter",
            "(404)-817-7070");
    private Shelter s10 = new Shelter(9, "Gateway Center", 330, "Men",
            -84.394529, 33.747618,
            "275 Pryor St. SW, Atlanta, GA 30303",
            "Shelter and recovery services",
            "(404) 215-6600");
    private Shelter s11 = new Shelter(10, "Young Adult Guidance Center", 12, "Young adults",
            -84.470567, 33.789157,
            "1230 Hightower Road NW Atlanta, GA 30318",
            "Emergency, Independent living, Restoration",
            "(404) 792-7616");
    private Shelter s12 = new Shelter(11, "Homes of Light", -1, "Veterans",
            -84.328691, 33.747641,
            "1800 Memorial Dr SE G3, Atlanta, GA 30317",
            "Veterans",
            "(404) 792-7616");


	private List<Shelter> shelters;
    public static final int TIMEOUT = 200; // 200ms

    @Before
    public void setUp() {
		/* FIREBASE */
        // mock Firebase for JUnit static method test in create manager

        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);
        /* END FIREBASE */

        // now manager can call the mock Firebase instance in its constructor
        manager = ShelterManager.getInstance();
        /* END FIREBASE */

		/* PRIVATE, REPLACE WITH YOUR INFORMATION */
        // to get private method into our current class
			/* Please replace your method name, and parameter.class for
			 * all your parameters
			 *
			 * e.g. 2 parameters: getDeclaredMethod("name", String.class, int.class)
			 */
        try {
            list = new ArrayList<>();

            Field field = ShelterManager.class.getDeclaredField("sheltersList");
            field.setAccessible(true);
            field.set(manager, list);

        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {

        }
		/* END PRIVATE */
    }

    private void addFemaleOnly() {
        list.add(s1);
        list.add(s2);
        list.add(s6);
    }

    private void addAll() {
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
        list.add(s8);
        list.add(s9);
        list.add(s10);
        list.add(s11);
        list.add(s12);
    }

    private void assertListEquals(List<Shelter> expected, List<Shelter> actual) {
        assertEquals(expected.size(), actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }


    @Test(timeout = TIMEOUT)
    public void testSearchGeneral() {
        addAll();
        List<Shelter> result = manager.searchSheltersWithoutName(Age.FAMILIES, Gender.UNSPECIFIED);
        List<Shelter> actual = new ArrayList<>();

        actual.add(s5);
        actual.add(s7);
        actual.add(s9);

        assertListEquals(result, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testAll() {
        addAll();
        List<Shelter> result = manager.searchSheltersWithoutName(Age.UNSPECIFIED, Gender.UNSPECIFIED);
        List<Shelter> actual = new ArrayList<>();

        actual.add(s1);
        actual.add(s2);
        actual.add(s3);
        actual.add(s4);
        actual.add(s5);
        actual.add(s6);
        actual.add(s7);
        actual.add(s8);
        actual.add(s9);
        actual.add(s10);
        actual.add(s11);
        actual.add(s12);

        assertListEquals(result, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testSearchNotFound() {
        addFemaleOnly();
        List<Shelter> emptyList = new ArrayList<>();
        List<Shelter> result = manager.searchSheltersWithoutName(Age.UNSPECIFIED, Gender.MALE);
        assertListEquals(emptyList, result);
    }


	/* END GENERIC */
}