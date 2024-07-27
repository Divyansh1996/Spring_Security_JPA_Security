package com.example.spring_security_jpa.controller;

import com.example.spring_security_jpa.model.Employee;
import com.example.spring_security_jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/index")
    public String Index(){
        return "index page";
    }

    @GetMapping("/about")
    public String About(){
        return "about page";
    }

    @GetMapping("/home")
    public String Home(){
        return "home page";
    }

    @PostMapping(value = "/addEmployee",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employeeRepository.save(employee);
        return employee;
    }
}
