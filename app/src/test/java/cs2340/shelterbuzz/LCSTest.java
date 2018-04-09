package cs2340.shelterbuzz;

import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Method;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import cs2340.shelterbuzz.model.ShelterManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;

public class LCSTest {
	private ShelterManager manager;
	private Method lcsMethod;

	private static final int TIMEOUT = 200; // 200ms

	@Before
	public void setUp() {
		manager = ShelterManager.getInstance();
		// to get private method into our current class
		try {
			lcsMethod = manager.getClass().
				getDeclaredMethod("longestCommonSubsequenceLength");
			lcsMethod.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = TIMEOUT)
	public void testEmptyString()
		throws InvocationTargetException, IllegalAccessException {
		assertEquals(0, lcsMethod.invoke(manager, "", ""));
	}

	@Test(timeout = TIMEOUT)
	public void testSubsequenceIsThere()
		throws InvocationTargetException, IllegalAccessException {
		// s1 is shorter than s2
		assertEquals(1, lcsMethod.invoke(manager, "a", "abc"));
		assertEquals(2, lcsMethod.invoke(manager, "we", "waters"));
		assertEquals(3, lcsMethod.invoke(manager, "obb", "bobbers"));
		assertEquals(4, lcsMethod.invoke(manager, "otil", "computational"));
		assertEquals(5, lcsMethod.invoke(manager, "mpnos", "lamperinnos"));
		assertEquals(10, lcsMethod.invoke(manager, "lamperinno", "lamperinnos"));

		// s2 is shorter than s1
		assertEquals(1, lcsMethod.invoke(manager, "abc", "a"));
		assertEquals(2, lcsMethod.invoke(manager, "waters", "we"));
		assertEquals(3, lcsMethod.invoke(manager, "bobbers", "obb"));
		assertEquals(4, lcsMethod.invoke(manager, "computational", "otil"));
		assertEquals(5, lcsMethod.invoke(manager, "lamperinnos", "mpnos"));
		assertEquals(10, lcsMethod.invoke(manager, "lamperinnos", "lamperinno"));

		// strings are equal
		assertEquals(3, lcsMethod.invoke(manager, "abc", "abc"));
	}

	@Test(timeout= TIMEOUT)
	public void testSubsequenceIsNotThere()
		throws InvocationTargetException, IllegalAccessException {
		// s1 is shorter than s2
		assertEquals(0, lcsMethod.invoke(manager, "bear", ""));
		assertEquals(0, lcsMethod.invoke(manager, "pony", "abc"));
		assertEquals(0, lcsMethod.invoke(manager, "yeji", "waters"));
		assertEquals(0, lcsMethod.invoke(manager, "teeny", "bobbers"));
		assertEquals(0, lcsMethod.invoke(manager, "bebe", "computational"));
		assertEquals(0, lcsMethod.invoke(manager, "zuzzc", "lamperinnos"));

		// s2 is shorter than s1
		assertEquals(0, lcsMethod.invoke(manager, "", "bear"));
		assertEquals(0, lcsMethod.invoke(manager, "abc", "pony"));
		assertEquals(0, lcsMethod.invoke(manager, "waters", "yeji"));
		assertEquals(0, lcsMethod.invoke(manager, "bobbers", "teeny"));
		assertEquals(0, lcsMethod.invoke(manager, "computational", "bebe"));
		assertEquals(0, lcsMethod.invoke(manager, "lamperinnos", "zuzzc"));

		// strings are equal
		assertEquals(3, lcsMethod.invoke(manager, "edf", "abc"));
		assertEquals(3, lcsMethod.invoke(manager, "abc", "edf"));
	}
}
