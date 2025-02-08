package com.archexpress.Demo.driver;

import com.archexpress.Demo.driver.database.Driver;
import com.archexpress.Demo.queue.ServiceBus;
import com.archexpress.Demo.queue.comnnads.AddDriverCommand;
import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverRepository driverRepository;
    private final ServiceBus serviceBus;

    public DriverController(DriverRepository driverRepository, ServiceBus serviceBus) {
        this.driverRepository = driverRepository;
        this.serviceBus = serviceBus;
    }

    @PostMapping
    @Caching(evict = {@CacheEvict(value = "driver", key = "'all'")})
    public String Create(@RequestBody Driver driver) {
        AddDriverCommand addDriverCommand = new AddDriverCommand();
        addDriverCommand.setDriver(driver);
        serviceBus.publishToQueue(addDriverCommand, "driver_registration_queue");
        return "Created..";
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

