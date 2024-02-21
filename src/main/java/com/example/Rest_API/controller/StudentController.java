package com.example.Rest_API.controller;

import com.example.Rest_API.entity.Student;
import com.example.Rest_API.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository repo;
    @GetMapping("/students")
    public List<Student> getAllStudents()
    {
        List<Student> students = repo.findAll();
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id)
    {
        Student student = repo.findById(id).get();
        return student;
    }

    @PostMapping("/student/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student)
    {
        repo.save(student);

    }

    @PutMapping("/student/edit/{id}")
    public Student updateStudents(@PathVariable int id)
    {
        Student student = repo.findById(id).get();
        student.setName("updated_name");
        repo.save(student);

        return student;

    }

    @DeleteMapping("/student/delete/{id}")
    public String deleteStudents(@PathVariable int id)
    {
        Student student = repo.findById(id).get();
        repo.delete(student);

        String name = student.getName();

        return "The user with name " + name + "has been deleted";

    }

    @GetMapping("/test")
    public String hello()
    {
        return "hello world! Anas here";
    }



}
