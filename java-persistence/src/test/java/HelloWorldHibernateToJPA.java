import com.msk.Message;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldHibernateToJPA {

    private static EntityManagerFactory createEntityManagerFactory() {
        Configuration cfg = new Configuration();

        cfg.configure().addAnnotatedClass(Message.class);

        Map<String, String> properties = new HashMap<String, String>();

        Enumeration<?> propertyNames =
                cfg.getProperties().propertyNames();

        while (propertyNames.hasMoreElements()) {

            String element = (String) propertyNames.nextElement();
            properties.put(element, cfg.getProperties().getProperty(element));
        }

        return Persistence.createEntityManagerFactory("ch02", properties);
    }
}
