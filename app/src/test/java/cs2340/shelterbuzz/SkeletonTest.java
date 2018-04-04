package cs2340.shelterbuzz;

/* Skeleton test file for ShelterManager class, 
 * with Firebase instance protection using Mockito. 
 *
 * Code is labeled as follows:
 *      FIREBASE: Code needed for Firebase simulation
 *      PRIVATE: Code needed to simulate private method calls
 *               e.g. if method being tested is not private, no need to use
 *      GENERAL: Generic JUnit code
 *      
 *  You may need to replace certain parts to fit your particular test case
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import cs2340.shelterbuzz.model.ShelterManager; // replace with your class

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


/* FIREBASE */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
/* END FIREBASE */
public class LCSTest {
	private ShelterManager manager;

	/* FIREBASE */
	@Mock
	private DatabaseReference mockedDatabaseReference;
	/* END FIREBASE */

	/* PRIVATE */
	private Method method;
	/* END PRIVATE */

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

		/* PRIVATE, REPLACE WITH YOUR INFORMATION */
		// to get private method into our current class
		try {
			/* Please replace your method name, and parameter.class for
			 * all your parameters
			 * 
			 * e.g. 2 parameters: getDeclaredMethod("name", String.class, int.class)
			 */
			method = manager.getClass().
				getDeclaredMethod("<Insert Method Name Here>", String.class, String.class);
			lcsMethod.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		/* END PRIVATE */
	}

	@Test(timeout = TIMEOUT)
	public void testEmptyString()
		throws InvocationTargetException, IllegalAccessException {
		// assertEquals(<expected result>, method.invoke(manager, <param>, <param>))
		assertEquals(0, lcsMethod.invoke(manager, "", ""));
	}

	@Test(timeout = TIMEOUT)
	public void testSubsequenceIsThere()
		throws InvocationTargetException, IllegalAccessException {
		// assertEquals(<expected result>, method.invoke(manager, <param>, <param>))
		assertEquals(1, method.invoke(manager, "a", "abc"));
	}
}
