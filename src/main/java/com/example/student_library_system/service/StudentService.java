package com.example.student_library_system.service;

import com.example.student_library_system.model.Card;
import com.example.student_library_system.model.Student;
import com.example.student_library_system.repository.StudentRepository;
import com.example.student_library_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String saveStudent(StudentRequestDto studentRequestDto){
        // convert the request dto into model class
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setDob(studentRequestDto.getDob());
        student.setDept(studentRequestDto.getDept());
        student.setEmail(studentRequestDto.getEmail());
        student.setGender(studentRequestDto.getGender());
        student.setSem(studentRequestDto.getSem());
        student.setMobile(studentRequestDto.getMobile());
        student.setAddress(studentRequestDto.getAddress());

        // whenever student adds, card also gets added as part of cascading
        Card card = new Card();
        card.setCardStatus("Active");
        card.setExpiryDate(LocalDateTime.now().plusYears(3).toString());

        student.setCard(card);
        card.setStudent(student);

        studentRepository.save(student);
        return "Student saved successfully";
    }
}
