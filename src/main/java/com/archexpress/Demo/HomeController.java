package com.archexpress.Demo;

import com.archexpress.Demo.employee.database.Employee;
import com.archexpress.Demo.queue.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    MessageSender messageSender;

    @GetMapping("/health")
    public String health(){
        return "System running OK!";
    }


    @GetMapping("/test/send-queue")
    public void testSendQueue(){
        Employee employee = new Employee();
        employee.setEmail("test@gmail.com");
        employee.setName("test");
        messageSender.sendMessage(employee, "def-exchange");
    }
}
