package com.archexpress.Demo.passenger;

import com.archexpress.Demo.passenger.database.Passenger;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerRepository passengerRepository;

    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @PostMapping
    @Caching(
            evict = {
                    @CacheEvict(value = "passenger", key = "'all'")
            }
    )
    public String Create(@RequestBody Passenger passenger) {
        passengerRepository.save(passenger);
//        messageSender.sendMessage(passenger);
        return "Created..";
    }

    @PutMapping
    @Caching(
            evict = {
                    @CacheEvict(value = "passenger", key = "'all'"),
                    @CacheEvict(value = "passenger", key = "#passenger.id")
            }
    )
    public String Update(@RequestBody Passenger passenger) {
//        messageSender.sendMessage(employee);
        passengerRepository.save(passenger);
        return "Updated...";
    }

    @GetMapping("/find-all")
    @Cacheable(value = "passenger", key = "'all'")
    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "passenger", key = "#id")
    public Passenger getById(@PathVariable ObjectId id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));
    }
}
