package jdbc.dao;

import jdbc.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void add(Employee employee);
    Employee get(long id);
    List<Employee> getAll();
    void update(Employee employee);
    void update(long id, Employee employee);
    void delete(long id);
    void delete(Employee employee);
}
