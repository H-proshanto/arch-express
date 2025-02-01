package com.archexpress.Demo.driver.database;

import com.archexpress.Demo.queue.MessageSender;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverRepository driverRepository;
    private final MessageSender messageSender;

    public DriverController(DriverRepository driverRepository, MessageSender messageSender) {
        this.driverRepository = driverRepository;
        this.messageSender = messageSender;
    }

    @PostMapping
    public String Create(@RequestBody Driver driver) {
        driverRepository.save(driver);
        return "Driver created";
    }

    @PutMapping
    @Caching(
            evict = {
                    @CacheEvict(value = "driver", key = "'all'"),
                    @CacheEvict(value = "driver", key = "#driver.id")
            }
    )
    public Driver Update(@RequestBody Driver driver) {
        driverRepository.save(driver);
        return driverRepository.save(driver);
    }

    @GetMapping
    @Cacheable(value = "driver", key = "'all'")
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "driver", key = "#id")
    public Driver getById(@PathVariable ObjectId id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }
}

