package jdbc.dao;

import jdbc.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAOImplEM implements EmployeeDAO{

    public EmployeeDAOImplEM() {
    }

    @Override
    public void add(Employee employee) {
        final String Query1 = "INSERT INTO employee (first_name, last_name, gender, age) " +
                "VALUES (:first_name, :last_name, :gender, :age)";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(Query1,Employee.class)
                .setParameter("first_name",employee.getFirstName())
                .setParameter("last_name",employee.getLastName())
                .setParameter("gender",employee.getGender())
                .setParameter("age",employee.getAge())
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    @Override
    public Employee get(long id) {
        final String jpqlQuery = "Select e FROM Employee e WHERE e.id = :id";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee;

        entityManager.getTransaction().begin();
        TypedQuery<Employee> employeeTypedQuery1 = entityManager.createQuery(jpqlQuery,Employee.class)
                .setParameter("id",id);

        employee = employeeTypedQuery1.getResultList().get(0);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList1;
        final String jpqlQuery = "Select e FROM Employee e";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Employee> employeeTypedQuery1 = entityManager.createQuery(jpqlQuery,Employee.class);

        employeeList1 = employeeTypedQuery1.getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return employeeList1;
    }

    @Override
    public void update(Employee employee) {
        final String jpqlQuery = "UPDATE Employee e " +
                "SET e.firstName = :first_name, " +
                "e.lastName = :last_name," +
                "e.gender = :gender," +
                "e.age = :age " +
                "WHERE e.id = :id";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery(jpqlQuery)
                .setParameter("first_name",employee.getFirstName())
                .setParameter("last_name",employee.getLastName())
                .setParameter("gender",employee.getGender())
                .setParameter("age",employee.getAge())
                .setParameter("id",employee.getId())
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void update(long id, Employee employee) {
        employee.setId(id);
        update(employee);
    }

    @Override
    public void delete(long id) {
         final String jpqlQuery = "DELETE FROM Employee e " +
                "WHERE e.id = :id";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery(jpqlQuery)
                .setParameter("id",id)
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delete(Employee employee) {
        final String jpqlQuery = "DELETE FROM Employee e " +
                "WHERE e.id = :id " +
                "AND e.firstName = :first_name " +
                "AND e.lastName = :last_name " +
                "AND e.gender = :gender " +
                "AND e.age = :age " +
                "AND e.city = :city";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery(jpqlQuery)
                .setParameter("id",employee.getId())
                .setParameter("first_name", employee.getFirstName())
                .setParameter("last_name", employee.getLastName())
                .setParameter("gender", employee.getGender())
                .setParameter("age", employee.getAge())
                .setParameter("city", employee.getCity())
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
