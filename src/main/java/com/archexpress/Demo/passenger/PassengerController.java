package com.archexpress.Demo.passenger;

import com.archexpress.Demo.passenger.database.Passenger;
import com.archexpress.Demo.queue.ServiceBus;
import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
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
    private final ServiceBus serviceBus;

    public PassengerController(PassengerRepository passengerRepository, ServiceBus serviceBus) {
        this.passengerRepository = passengerRepository;
        this.serviceBus = serviceBus;
    }

    @PostMapping
    @Caching(
            evict = {
                    @CacheEvict(value = "passenger", key = "'all'")
            }
    )
    public String Create(@RequestBody Passenger passenger) {
        AddPassengerCommand addPassengerCommand = new AddPassengerCommand();
        addPassengerCommand.setPassenger(passenger);
        serviceBus.publishToQueue(addPassengerCommand, "passenger_registration_queue");
        return "Created..";
    }

//    @PutMapping
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "passenger", key = "'all'"),
//                    @CacheEvict(value = "passenger", key = "#passenger.id")
//            }
//    )
//    public String Update(@RequestBody Passenger passenger) {
////        messageSender.sendMessage(employee);
//        passengerRepository.save(passenger);
//        return "Updated...";
//    }
//
//    @GetMapping("/find-all")
//    @Cacheable(value = "passenger", key = "'all'")
//    public List<Passenger> getAll() {
//        return passengerRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    @Cacheable(value = "passenger", key = "#id")
//    public Passenger getById(@PathVariable ObjectId id) {
//        return passengerRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));
//    }    @PutMapping
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "passenger", key = "'all'"),
//                    @CacheEvict(value = "passenger", key = "#passenger.id")
//            }
//    )
//    public String Update(@RequestBody Passenger passenger) {
////        messageSender.sendMessage(employee);
//        passengerRepository.save(passenger);
//        return "Updated...";
//    }
//
//    @GetMapping("/find-all")
//    @Cacheable(value = "passenger", key = "'all'")
//    public List<Passenger> getAll() {
//        return passengerRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    @Cacheable(value = "passenger", key = "#id")
//    public Passenger getById(@PathVariable ObjectId id) {
//        return passengerRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found"));
//    }
}
