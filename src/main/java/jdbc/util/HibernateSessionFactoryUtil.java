package jdbc.util;

import jdbc.model.City;
import jdbc.model.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    public HibernateSessionFactoryUtil() {
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try{
                Configuration configuration1 = new Configuration().configure();
                configuration1.addAnnotatedClass(Employee.class);
                configuration1.addAnnotatedClass(City.class);
                StandardServiceRegistryBuilder standardServiceRegistryBuilder1 = new StandardServiceRegistryBuilder().applySettings(configuration1.getProperties());
                sessionFactory = configuration1.buildSessionFactory(standardServiceRegistryBuilder1.build());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
