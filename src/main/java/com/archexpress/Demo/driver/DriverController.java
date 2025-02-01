package com.archexpress.Demo.driver;

import com.archexpress.Demo.driver.database.Driver;
import com.archexpress.Demo.queue.QueuePublisher;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverRepository driverRepository;
    private final QueuePublisher queuePublisher;

    public DriverController(DriverRepository driverRepository, QueuePublisher queuePublisher) {
        this.driverRepository = driverRepository;
        this.queuePublisher = queuePublisher;
    }

    @PostMapping
    @Caching(evict = {@CacheEvict(value = "driver", key = "'all'")})
    public String Create(@RequestBody Driver driver) {
        queuePublisher.publish(driver, "driver_registration_exchange");
        return "Driver event published";
    }

//    @PutMapping
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "driver", key = "'all'"),
//                    @CacheEvict(value = "driver", key = "#driver.id")
//            }
//    )
//    public Driver Update(@RequestBody Driver driver) {
//        driverRepository.save(driver);
//        return driverRepository.save(driver);
//    }
//
//    @GetMapping
//    @Cacheable(value = "driver", key = "'all'")
//    public List<Driver> getAll() {
//        return driverRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    @Cacheable(value = "driver", key = "#id")
//    public Driver getById(@PathVariable ObjectId id) {
//        return driverRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
//    }
}

