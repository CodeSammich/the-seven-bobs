package cs2340.shelterbuzz;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import cs2340.shelterbuzz.model.ShelterManager;
import cs2340.shelterbuzz.model.Age;


/* END GENERIC */

/* FIREBASE */
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
/* END FIREBASE */

/* PRIVATE */
import java.lang.reflect.Method;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;
/**
 * This class are JUnit tests for the ShelterManager's containsAge method.
 * @author Ye-ji Kim
 * @version 1.0
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})

public class AgeTest {

    private ShelterManager manager;

    /* FIREBASE */
    @Mock
    private DatabaseReference mockedDatabaseReference;
	/* END FIREBASE */

    /* PRIVATE */
    private Method method;
	/* END PRIVATE */

    public static final int TIMEOUT = 200; // 200ms

    /**
     * Method used for setting up the mock Firebase and reference to the containsAge method needed
     * for the JUnits tests.
     */
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

		/* PRIVATE, REPLACE WITH YOUR INFORMATION */
        // to get private method into our current class
        try {
            method = manager.getClass().
                    getDeclaredMethod("containsAge", String.class, Age.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

//        catch (IllegalAccessException e) {
//            System.out.println("Illegal Access Exception");
//        } catch (InvocationTargetException e) {
//            System.out.println("Invocation Target Exception");
//        }
		/* END PRIVATE */
    }



//    UNSPECIFIED(""),
//    FAMILIES("Families", "Families w/", "Families with", "Family"),
//    CHILDREN("Children", "Childrens", "Child", "Newborn"),
//    TEENS("Young adults", "Young Adults", "Teenagers", "Teens"),
//    VETERAN("Veterans", "Vet", "Veteran", "Military"),
//    ANYONE("Anyone", "Everyone", "All");


    //boolean containsAge(String restrictions, Age age)


    /* GENERIC */

    /**
     * Various JUnits tests for ShelterManager's containsAge method
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void testUnspecified()
    throws IllegalAccessException, InvocationTargetException {

        assertTrue((boolean) (method.invoke(manager,"", Age.UNSPECIFIED)));

        //FAMILIES, CHILDREN, TEENS, VETERAN, ANYONE
        assertFalse((boolean) (method.invoke(manager,"A", Age.UNSPECIFIED)));
        assertFalse((boolean) (method.invoke(manager,"Families", Age.UNSPECIFIED)));
        assertFalse((boolean) (method.invoke(manager,"Children", Age.UNSPECIFIED)));
        assertFalse((boolean) (method.invoke(manager,"Young adults", Age.UNSPECIFIED)));
        assertFalse((boolean) (method.invoke(manager,"Veterans", Age.UNSPECIFIED)));
        assertFalse((boolean) (method.invoke(manager,"Anyone", Age.UNSPECIFIED)));

    }






	/* END GENERIC */
}



