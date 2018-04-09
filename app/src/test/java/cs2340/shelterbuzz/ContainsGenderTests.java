package cs2340.shelterbuzz;

/* GENERIC */
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import cs2340.shelterbuzz.model.ShelterManager; // replace with your class
import cs2340.shelterbuzz.model.Gender;
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
/* END PRIVATE */

/**
 * JUnits tests for ShelterManager's containsGender method.
 * @author Tony Wu
 * @version 1.0
 */

/* FIREBASE */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
/* END FIREBASE */

public class ContainsGenderTests {
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
     * Method used for setting up the mock Firebase and reference to the containsGender method needed
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
			/* Please replace your method name, and parameter.class for
			 * all your parameters
			 *
			 * e.g. 2 parameters: getDeclaredMethod("name", String.class, int.class)
			 */
            method = manager.getClass().
                    getDeclaredMethod("containsGender", String.class, Gender.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
		/* END PRIVATE */
    }

    /**
     * Test that checks if the restriction "Women/Children" allows for Males, should throw false.
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
            public void testMaleWithWomenChildren() throws IllegalAccessException, InvocationTargetException {
            //assertFalse((Boolean) containsGender.invoke(instance,"Women/Children", Gender.MALE));
            assertFalse((Boolean) (method.invoke(manager,"Women/Children", Gender.MALE)));
    }

    /**
     * Test that checks if the restriction "Women/Children" allows for Gender Fluid, should throw false.
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
            public void testGenderFluidWithWomanChildren() throws IllegalAccessException, InvocationTargetException{
            //assertFalse((Boolean) containsGender.invoke(instance, "Women/Children", Gender.GENDER_FLUID));
            assertFalse((Boolean) (method.invoke(manager,"Women/Children", Gender.GENDER_FLUID)));
    }

    /**
     * Test that checks if the restriction "Women/Children" allows for Females, should throw true.
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
            public void testFemaleWithWomenChildren() throws IllegalAccessException, InvocationTargetException {
            //assertTrue((Boolean) containsGender.invoke(instance, "Women/Children", Gender.FEMALE));
            assertTrue((Boolean) (method.invoke(manager,"Women/Children", Gender.FEMALE)));
    }

    /**
     * Test that checks if the restriction "Women/Children" allows for Gender Neutral, should throw false.
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void testGenderNeutralWithWomenChildren() throws IllegalAccessException, InvocationTargetException {
        //assertFalse((Boolean) containsGender.invoke(instance, "Women/Children", Gender.GENDER_NEUTRAL));
        assertFalse((Boolean) (method.invoke(manager,"Women/Children", Gender.GENDER_NEUTRAL)));
    }

    /**
     * Test that checks if the restriction "Men" allows for Males, should throw true.
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void testMaleWithMen() throws IllegalAccessException, InvocationTargetException {
        //assertTrue((Boolean) containsGender.invoke(instance, "MEN", Gender.MALE));
        assertTrue((Boolean) (method.invoke(manager,"MEN", Gender.MALE)));
    }

    /**
     * Test that checks if the restriction "Men" allows for Females, should throw false.
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void testFemaleWithMen() throws IllegalAccessException, InvocationTargetException {
        //assertFalse((Boolean) containsGender.invoke(instance, "Men", Gender.FEMALE));
        assertFalse((Boolean) (method.invoke(manager,"MEN", Gender.FEMALE)));
    }

    /**
     * General tests for the containsGender method..
     * @throws IllegalAccessException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void GeneralTests() throws IllegalAccessException, InvocationTargetException {
        //assertFalse((Boolean) containsGender.invoke(instance, "Men", Gender.FEMALE));
        assertFalse((Boolean) (method.invoke(manager,"MEn", Gender.FEMALE)));
        //assertTrue((Boolean) containsGender.invoke(instance, "Anyone", Gender.FEMALE));
        assertTrue((Boolean) (method.invoke(manager,"ANyonE", Gender.FEMALE)));
        //assertTrue((Boolean) containsGender.invoke(instance, "Anyone", Gender.MALE));
        assertTrue((Boolean) (method.invoke(manager,"AnyoNE", Gender.GENDER_NEUTRAL)));
    }



}