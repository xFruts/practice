package ru.maxow.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxow.weather.entity.Weather;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
  Optional<Weather> findByLatitudeAndLongitude(double latitude, double longitude);
}
