package jdbc.dao;

import jdbc.model.City;

public interface CityDAO {

    void add(City city);
    City get(long id);
    void update(City city);
    void update(long id, City city);
    void delete(City city);
    void delete(long id);
}
