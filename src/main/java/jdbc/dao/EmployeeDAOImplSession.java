package jdbc.dao;

import jdbc.model.Employee;
import jdbc.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EmployeeDAOImplSession implements EmployeeDAO{

    public EmployeeDAOImplSession() {
    }

    @Override
    public void add(Employee employee) {
        try (Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction1 = session1.beginTransaction();
            session1.save(employee);
            transaction1.commit();
        }
    }

    @Override
    public Employee get(long id) {
        try (Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session1.get(Employee.class,id);
        }
    }

    @Override
    public List<Employee> getAll() {
        try(Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){

            CriteriaBuilder criteriaBuilder1 = session1.getCriteriaBuilder();
            CriteriaQuery<Employee> employeeCriteriaQuery1 = criteriaBuilder1.createQuery(Employee.class);
            employeeCriteriaQuery1.from(Employee.class);

            return  session1.createQuery(employeeCriteriaQuery1).getResultList();
        }
    }

    @Override
    public void update(Employee employee) {
        try(Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction1 = session1.beginTransaction();
            if(session1.get(Employee.class,employee.getId()) != null){
                session1.clear();
                session1.update(employee);
            }
            transaction1.commit();
        }
    }

    @Override
    public void update(long id, Employee employee) {
        employee.setId(id);
        update(employee);
    }

    @Override
    public void delete(long id) {
        try(Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction1 = session1.beginTransaction();
            Employee employee1 = session1.get(Employee.class,id);
            if(employee1 != null){
                session1.delete(employee1);
            }
            transaction1.commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        if(employee.getId() == 0){
            throw new RuntimeException("Employee ID = 0");
        }else{
            delete(employee.getId());
        }
    }
}
