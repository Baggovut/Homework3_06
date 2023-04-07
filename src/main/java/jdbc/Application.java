package jdbc;

import jdbc.dao.CityDAOImpl;
import jdbc.dao.EmployeeDAOImplEM;
import jdbc.dao.EmployeeDAOImplSession;
import jdbc.model.City;
import jdbc.model.Employee;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        EmployeeDAOImplEM employeeDAOImplEM1 = new EmployeeDAOImplEM();
        EmployeeDAOImplSession employeeDAOImplSession1 = new EmployeeDAOImplSession();

        CityDAOImpl cityDAOImpl1 = new CityDAOImpl();
        City city1 = cityDAOImpl1.get(1);
        City city2 = cityDAOImpl1.get(2);
        City city3 = cityDAOImpl1.get(3);

        System.out.println("\nСоздание (добавление) сущности Employee в таблицу.");
        Employee employee11 = new Employee("Имя1","Фамилия1",22,"муж.",city2);
        Employee employee12 = new Employee("Имя122","Фамилия122",22,"муж.",city2);
        employeeDAOImplEM1.add(employee11);
        employeeDAOImplSession1.add(employee12);

        System.out.println("\nПолучение конкретного объекта Employee по id.");
        Employee employee21 = employeeDAOImplEM1.get(4);
        Employee employee22 = employeeDAOImplSession1.get(6);
        System.out.println(employee21);
        System.out.println(employee22);

        System.out.println("\nПолучение списка всех объектов Employee из базы.");
        System.out.println(employeeDAOImplEM1.getAll());
        System.out.println(employeeDAOImplSession1.getAll());

        System.out.println("\nИзменение конкретного объекта Employee в базе по id.");
        Employee employee31 = new Employee("Имя331","Фамилия33",32,"муж.",city3);
        Employee employee32 = new Employee("Имя31","Фамилия334",32,"муж.",city3);
        employeeDAOImplEM1.update(10, employee31);
        System.out.println(employeeDAOImplEM1.get(10));
        employeeDAOImplSession1.update(33, employee32);
        System.out.println(employeeDAOImplSession1.get(14));

        System.out.println("\nУдаление конкретного объекта Employee из базы по id.");
        employeeDAOImplEM1.delete(17);
        employeeDAOImplSession1.delete(19);

        System.out.println("\nУдаление конкретного объекта Employee из базы.");
        Employee employee41 = new Employee(21,"Имя1","Фамилия1",22,"муж.",city1);
        Employee employee42 = new Employee(26,"Имя1","Фамилия1",22,"муж.",city1);
        employeeDAOImplEM1.delete(employee41);
        employeeDAOImplSession1.delete(employee42);

        System.out.println("Список сотрудников в city1");
        System.out.println(city1);
        List<Employee> employeeList1 = city1.getEmployee();
        System.out.println(employeeList1);


        City newCity = new City("Тестовый город");
        cityDAOImpl1.add(newCity);
        Employee newEmployee = new Employee("Имя9","Фамилия9",33,"жен.", cityDAOImpl1.get(13));
        employeeDAOImplSession1.add(newEmployee);
        System.out.println("Удаляет экземпляр City из базы данных ссылающихся на него сотрудников.");
        cityDAOImpl1.delete(13);
    }
}