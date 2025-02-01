package com.archexpress.Demo.employee.database;

import com.archexpress.Demo.queue.MessageSender;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final MessageSender messageSender;

    public EmployeeController(EmployeeRepository employeeRepository, MessageSender messageSender) {
        this.employeeRepository = employeeRepository;
        this.messageSender = messageSender;
    }

    @PostMapping
    public String Create(@RequestBody Employee employee) {
        messageSender.sendMessage(employee);
        return "Created..";
    }

    @PutMapping
    @Caching(
            evict = {
                    @CacheEvict(value = "employee", key = "'all'"),
                    @CacheEvict(value = "employee", key = "#employee.id")
            }
    )
    public Employee Update(@RequestBody Employee employee) {
        messageSender.sendMessage(employee);
        return employeeRepository.save(employee);
    }

    @GetMapping("/find-all")
    @Cacheable(value = "employee", key = "'all'")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "employee", key = "#id")
    public Employee getById(@PathVariable ObjectId id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }
}
