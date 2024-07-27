package com.example.spring_security_jpa.service;

import com.example.spring_security_jpa.model.CustomEmployee;
import com.example.spring_security_jpa.model.Employee;
import com.example.spring_security_jpa.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Employee employee = employeeRepository.findByEmail(email);
         if(employee == null){
             throw new UsernameNotFoundException("User with email "+email+" not found");
         }
         return new CustomEmployee(employee);
    }
}
