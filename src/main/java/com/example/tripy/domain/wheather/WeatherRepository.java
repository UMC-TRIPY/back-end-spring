package com.example.tripy.domain.wheather;

import jakarta.persistence.Tuple;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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


    @Query("SELECT w.temp as temp,w.weatherMain as weatherMain " +
        "FROM Weather w " +
        "WHERE w.cityName = :cityName " +
        "AND w.weatherDate = :currentDate " +
        "AND w.weatherTime <= :currentTime " +
        "ORDER BY w.weatherDate DESC " +
        "LIMIT 1")
    Tuple findClosestTemperature(String cityName, LocalDate currentDate, LocalTime currentTime);
}