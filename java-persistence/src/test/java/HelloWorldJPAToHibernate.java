import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;

public class HelloWorldJPAToHibernate {

    // Obtain a SessionFactory (Hibernate) from an EntityManagerFactory (JPA)
    public static SessionFactory getSessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }
}
