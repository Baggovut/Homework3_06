package jdbc.dao;

import jdbc.model.City;
import jdbc.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CityDAOImpl implements CityDAO{
    public CityDAOImpl() {
    }

    @Override
    public void add(City city) {
        try (Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction1 = session1.beginTransaction();
            session1.save(city);
            transaction1.commit();
        }
    }

    @Override
    public City get(long id) {
        try (Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session1.get(City.class,id);
        }
    }

    @Override
    public void update(City city) {
        try(Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction1 = session1.beginTransaction();
            if(session1.get(City.class,city.getCityId()) != null){
                session1.clear();
                session1.update(city);
            }
            transaction1.commit();
        }
    }

    @Override
    public void update(long id, City city) {
        city.setCityId(id);
        update(city);
    }

    @Override
    public void delete(City city) {
        if(city.getCityId() == 0){
            throw new RuntimeException("City ID = 0");
        }else{
            delete(city.getCityId());
        }
    }

    @Override
    public void delete(long id) {
        try(Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction1 = session1.beginTransaction();
            City city1 = session1.get(City.class,id);
            if(city1 != null){
                session1.delete(city1);
            }
            transaction1.commit();
        }
    }
}
