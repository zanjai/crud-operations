package com.example.springDemo.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class  StudentService {
    private final StudentRepository studentRepository;
    @Autowired

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(exist) studentRepository.deleteById(studentId);
        else throw new IllegalStateException("Id not exist");
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student id does not exist"));
        if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> searchForEmail = studentRepository.findStudentByEmail(email);
            if(!searchForEmail.isPresent()) student.setEmail(email);
            else throw new IllegalStateException("Already Present");
        }
    }
}
