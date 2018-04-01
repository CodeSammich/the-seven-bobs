package cs2340.shelterbuzz;

import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import cs2340.shelterbuzz.model.ShelterManager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;


/**
 * Tests checkIn, which is an instance method of ShelterManager
 */
public class YounTests {

    ShelterManager instance;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        try {
            instance = ShelterManager.getInstance();
            Method privateStringMethod = ShelterManager.class.
                    getDeclaredMethod("longestCommonSubsequenceLength");
            privateStringMethod.setAccessible(true);

            // To invoke the method...
            String retValue = (String) privateStringMethod.invoke(instance);
        } catch (NoSuchMethodException e) {
            System.out.println("Method doesn't exist");
        } catch (IllegalAccessException e) {
            System.out.println("Illegal Access Exception");
        } catch (InvocationTargetException e) {
            System.out.println("Invocation Target Exception");
        }
    }










}
