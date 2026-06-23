package ru.maxow.location.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.maxow.location.model.Location;
import ru.maxow.location.model.Weather;
import ru.maxow.location.repository.LocationRepository;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepository repository;
    private final RestTemplate restTemplate;

    @GetMapping
    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    @GetMapping(params = "name")
    public Location getLocationByName(@RequestParam String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Location not found: " + name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody Location location) {
        return repository.save(location);
    }

    @PutMapping(params = "name")
    public Location updateLocation(@RequestParam String name, @RequestBody Location updatedLocation) {
        Location existingLocation = repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Location not found: " + name));
        
        existingLocation.setLatitude(updatedLocation.getLatitude());
        existingLocation.setLongitude(updatedLocation.getLongitude());
        existingLocation.setName(updatedLocation.getName());
        
        return repository.save(existingLocation);
    }

    @DeleteMapping(params = "name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@RequestParam String name) {
        Location location = repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Location not found: " + name));
        repository.delete(location);
    }

    @GetMapping("/weather")
    public Weather getWeatherByLocationName(@RequestParam String name) {
        Location location = repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Location not found: " + name));
        
        String url = String.format("http://localhost:8082/weather?lat=%s&lon=%s", 
            location.getLatitude(), location.getLongitude());
        
        return restTemplate.getForObject(url, Weather.class);
    }
}
