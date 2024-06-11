package chapter04.general;

import chapter04.model.SimpleObject;
import com.msk.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class SaveLoadTest {

    @Test
    public void testSaveLoad() {
        Long id = null;
        SimpleObject obj;

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            obj = new SimpleObject();
            obj.setKey("s1");
            obj.setValue(10L);

            session.save(obj);
            assertNotNull(obj.getId());
            // we should have an id now, set by session.save().
            id = obj.getId();

            tx.commit();

            try (Session session2 = SessionUtil.getSession()) {
                // we are loading the object by id.
                SimpleObject o2 = session2.load(SimpleObject.class, id);
                assertEquals(o2.getKey(), "s1");
                assertNotNull(o2.getValue());
                assertEquals(o2.getValue().longValue(), 10L);

                SimpleObject o3 = session2.load(SimpleObject.class, id);

                // Since o2 and o3 were loaded in the same session, they're not only
                // equivalent, as shown by equals(), but equal, as shown by ==.
                // Since obj was not loaded in this session, it is equivalent but not ==.

                assertEquals(o2, o3);
                assertNotEquals(obj, o2);
                assertNotEquals(obj, o3);

                assertSame(o2, o3);
                assertFalse(o2 == obj);

                assertNotSame(obj, o3);
                assertFalse(obj == o3);
            }
        }
    }
}
