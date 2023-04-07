package jdbc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private long cityId;
    @Column(name = "city_name")
    private String cityName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    //Если в данной реализации выбрать FetchType.LAZY, то поле сразу не загрузится.
    //И когда будет происходить обращение к значению этого поля, будет произведена попытка его загрузки,
    //но из-за того, что сессия уже закрыта, будет вызвано исключение.
    //Поэтому я в данном случае выбрал FetchType.EAGER, для того,
    //чтобы значение поля загрузилось во время создания объекта, а не во время его чтения.
    @JoinColumn(name = "city_id")
    private List<Employee> employee;

    public City() {
    }

    public City(String cityName) {
        this.cityId = 0;
        this.cityName = cityName;
    }

    public City(long cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + getCityId() +
                ", city_name='" + getCityName() + '\'' +
                '}';
    }

    public long getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Employee> getEmployee() {
        return employee;
    }
}
