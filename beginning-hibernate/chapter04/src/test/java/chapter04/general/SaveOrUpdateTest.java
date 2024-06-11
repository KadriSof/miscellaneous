package chapter04.general;

import chapter04.model.SimpleObject;
import com.msk.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SaveOrUpdateTest {

    @Test
    public void testSaveOrUpdateEntity() {
        Long id;
        SimpleObject obj;

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            // this only work for simple objects.
            session.createQuery("delete from SimpleObject").executeUpdate();

            tx.commit();
        }

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            obj = new SimpleObject();
            obj.setKey("Open Source and Standards");
            obj.setValue(14L);

            session.save(obj);
            assertNotNull(obj.getId());

            id = obj.getId();

            tx.commit();
        }

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            obj.setValue(12L);

            // if key didn't exist in the database, it would after this call.
            session.saveOrUpdate(obj);

            tx.commit();
        }

        // saveOrUpdate() will update a row in the database if one matches.
        // this is what one usually expects.
        assertEquals(id, obj.getId());

        try (Session session = SessionUtil.getSession()) {
            List<SimpleObject> objects = session
                    .createQuery("from SimpleObject", SimpleObject.class)
                    .list();

            assertEquals(objects.size(), 1);
        }
    }
}
