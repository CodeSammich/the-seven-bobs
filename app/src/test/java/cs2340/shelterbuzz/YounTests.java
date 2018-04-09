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

import cs2340.shelterbuzz.model.Shelter;
import cs2340.shelterbuzz.model.ShelterManager; // replace with your class
/* END GENERIC */

/* FIREBASE */
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static org.junit.Assert.assertTrue;
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
import java.lang.reflect.Method;

import java.lang.reflect.InvocationTargetException;
import java.lang.IllegalAccessException;
import java.util.ArrayList;
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

    /* FIREBASE */
    @Mock
    private DatabaseReference mockedDatabaseReference;
	/* END FIREBASE */

    /* PRIVATE */
    private Method method;
	/* END PRIVATE */
	private Shelter shelter;

    public static final int TIMEOUT = 200; // 200ms

    @Before
    public void setUp() {
		/* FIREBASE */
        // mock Firebase for JUnit static method test in create manager
        Integer shelterId = 0;
        shelter = new Shelter(shelterId, "The Zone", 300, "anyone",
                0, 90.0, "North Pole", "anyone is welcome", "8434576631");
        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        Query mockedQuery = Mockito.mock(Query.class);

        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);
        when(mockedDatabaseReference.orderByChild(anyString())).thenReturn(mockedDatabaseReference);
        //when(mockedDatabaseReference.equalTo(anyString())).thenReturn(shelter);

        /* END FIREBASE */

		/* PRIVATE, REPLACE WITH YOUR INFORMATION */
        // to get private method into our current class
        try {
			/* Please replace your method name, and parameter.class for
			 * all your parameters
			 *
			 * e.g. 2 parameters: getDeclaredMethod("name", String.class, int.class)
			 */
            // Add a single Shelter
            mockedDatabaseReference.child("shelters").child(shelterId.toString()).setValue(shelter);
            doAnswer(new Answer<Void>() {
                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
                    DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                    when(mockedDataSnapshot.getValue(Shelter.class)).thenReturn(shelter);

                    List<DataSnapshot> list = new ArrayList();
                    list.add(mockedDataSnapshot);
                    when(mockedDataSnapshot.getChildren()).thenReturn(list);

                    valueEventListener.onDataChange(mockedDataSnapshot);
                    //valueEventListener.onCancelled(...);

                    return null;
                }
            }).when(mockedDatabaseReference).addValueEventListener(any(ValueEventListener.class));

            doAnswer(new Answer<Void>() {
                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
                    DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                    when(mockedDataSnapshot.getValue(Shelter.class)).thenReturn(shelter);

                    List<DataSnapshot> list = new ArrayList();
                    list.add(mockedDataSnapshot);
                    when(mockedDataSnapshot.getChildren()).thenReturn(list);

                    valueEventListener.onDataChange(mockedDataSnapshot);
                    //valueEventListener.onCancelled(...);

                    return null;
                }
            }).when(mockedDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));

            // now manager can call the mock Firebase instance in its constructor
            manager = ShelterManager.getInstance();
            method = manager.getClass().
                    getDeclaredMethod("checkIn", Integer.class, Integer.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
		/* END PRIVATE */
    }

    /* GENERIC */
    @Test(timeout = TIMEOUT)
    public void testNumBedsValid()
            throws InvocationTargetException, IllegalAccessException {
        System.out.println(manager.get(0));
        method.invoke(manager, 0, 30);
    }
	/* END GENERIC */
}