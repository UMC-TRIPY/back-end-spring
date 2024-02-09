package com.example.tripy.domain.wheather;

import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("SELECT w.cityName, MAX(w.temp) as tempMax, MIN(w.temp) as tempMin, w.weatherDate, " +
            "(SELECT w2.weatherMain " +
            "FROM Weather w2 " +
            "WHERE w2.cityName = w.cityName AND w2.weatherDate = w.weatherDate " +
            "GROUP BY w2.weatherMain " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 1"
        + ") as subquery " +
        "FROM Weather w " +
        "WHERE w.cityName = :cityName " +
        "GROUP BY w.cityName, w.weatherDate "
        )
    List<Object[]> findAllByCityNameAndByDateTime(String cityName);

}
