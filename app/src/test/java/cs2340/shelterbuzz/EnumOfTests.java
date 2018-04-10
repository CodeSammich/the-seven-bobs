package cs2340.shelterbuzz;

import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Method;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import cs2340.shelterbuzz.model.Gender;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;

//for Gender class

/**
 * Tests for enum of in the gender class
 */
public class EnumOfTests {

    public static final int TIMEOUT = 200; // 200ms

    /**
     * test case that checks if the correct Gender enum is returned based on a string
     * identifier that may be associated with a shelter
     * @throws InvocationTargetException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void testContainsWord()
            throws InvocationTargetException, IllegalAccessException {
        assertEquals(Gender.MALE, Gender.enumOf("Men"));
        assertEquals(Gender.MALE, Gender.enumOf("Male"));
        assertEquals(Gender.FEMALE, Gender.enumOf("Women"));
        assertEquals(Gender.FEMALE, Gender.enumOf("Female"));
        assertEquals(Gender.GENDER_FLUID, Gender.enumOf("Fluid"));
        assertEquals(Gender.GENDER_NEUTRAL, Gender.enumOf("Agender"));
        assertEquals(Gender.GENDER_NEUTRAL, Gender.enumOf("Neutral"));
    }

    /**
     * test that checks if the method returns null if that word cannot be found in the
     * gender enums
     *
     * @throws InvocationTargetException if test does not have access to the method being invoked
     * @throws InvocationTargetException when invoking a private method throws an exception
     */
    @Test(timeout = TIMEOUT)
    public void testDoesNotContainWord()
        throws InvocationTargetException, IllegalAccessException {
        assertEquals(null, Gender.enumOf("Memerino"));
        assertEquals(null, Gender.enumOf("Agemder"));
    }

}
