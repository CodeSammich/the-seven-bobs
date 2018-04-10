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
public class EnumOfTests {

    public static final int TIMEOUT = 200; // 200ms

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
        assertEquals(Gender.MALE, Gender.enumOf("Me"));
    }

    @Test(timeout = TIMEOUT)
    public void testDoesNotContainWord()
        throws InvocationTargetException, IllegalAccessException {
        assertEquals(null, Gender.enumOf("Memerino"));
        assertEquals(null, Gender.enumOf("Agemder"));
    }

}
