package com.example.springDemo.Student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student sriharish = new Student("Sriharish", "sriharishoffl@gmail.com", LocalDate.of(2002, Month.OCTOBER, 24));
            Student varun = new Student("Varun", "varunoffl@gmail.com", LocalDate.of(2002, Month.OCTOBER, 24));
            repository.saveAll(List.of(sriharish, varun));
        };

    }
}
