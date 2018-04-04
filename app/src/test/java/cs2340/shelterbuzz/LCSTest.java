package cs2340.shelterbuzz;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.junit.Before;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.lang.reflect.Method;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;

import cs2340.shelterbuzz.model.ShelterManager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
public class LCSTest {
	private ShelterManager manager;
	private Method lcsMethod;

	@Mock
	private DatabaseReference mockedDatabaseReference;

    public static final int TIMEOUT = 200; // 200ms

	@Before
	public void setUp() {
        // mock Firebase for JUnit static method test in create manager
		mockedDatabaseReference = Mockito.mock(DatabaseReference.class);
		
		FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
		when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);
		
		PowerMockito.mockStatic(FirebaseDatabase.class);
		when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);

        // now manager can call the mock Firebase instance in its constructor
		manager = ShelterManager.getInstance();

		// to get private method into our current class
		try {
			lcsMethod = manager.getClass().
				getDeclaredMethod("longestCommonSubsequenceLength", String.class, String.class);
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
		assertEquals(0, lcsMethod.invoke(manager, "yoji", "waters"));
		assertEquals(0, lcsMethod.invoke(manager, "taany", "bobbers"));
		assertEquals(0, lcsMethod.invoke(manager, "bebe", "computational"));
		assertEquals(0, lcsMethod.invoke(manager, "zuzzc", "lamperinnos"));

		// s2 is shorter than s1
		assertEquals(0, lcsMethod.invoke(manager, "", "bear"));
		assertEquals(0, lcsMethod.invoke(manager, "abc", "pony"));
		assertEquals(0, lcsMethod.invoke(manager, "waters", "yoji"));
		assertEquals(0, lcsMethod.invoke(manager, "bobbers", "taany"));
		assertEquals(0, lcsMethod.invoke(manager, "computational", "bebe"));
		assertEquals(0, lcsMethod.invoke(manager, "lamperinnos", "zuzzc"));

		// strings are equal size
		assertEquals(0, lcsMethod.invoke(manager, "edf", "abc"));
		assertEquals(0, lcsMethod.invoke(manager, "abc", "edf"));
	}
}
