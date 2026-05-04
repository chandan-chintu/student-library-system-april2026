package com.example.student_library_system.controller;

import com.example.student_library_system.model.Student;
import com.example.student_library_system.requestdto.StudentRequestDto;
import com.example.student_library_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.saveStudent(studentRequestDto);
        return  response;
    }

    @GetMapping("/findById/{id}")
    public Object findStudentById(@PathVariable int id){
        try {
            Student student = studentService.getStudentById(id);
            return student;
        } catch (Exception e){
            System.out.println("exception occurred : "+e.getMessage());
            return "exception occurred : "+e.getMessage();
        }
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return studentList;
    }

    @GetMapping("/findByPage")
    public List<Student> findAllStudentsByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        List<Student> studentList = studentService.getStudentByPage(pageNo, pageSize);
        return studentList;
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.updateStudent(id, studentRequestDto);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id){
        String response = studentService.deleteStudentById(id);
        return response;
    }

    @GetMapping("/findByDept")
    public List<Student> findStudentByDept(@RequestParam String dept){
        List<Student> studentList = studentService.getStudentByDept(dept);
        return studentList;
    }

    @GetMapping("/findByEmail")
    public Student getStudentByEmail(@RequestParam String email){
        Student student = studentService.getStudentByEmail(email);
        return student;
    }
}
