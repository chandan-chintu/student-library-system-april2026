package com.example.student_library_system.service;

import com.example.student_library_system.model.Card;
import com.example.student_library_system.model.Student;
import com.example.student_library_system.repository.StudentRepository;
import com.example.student_library_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Student getStudentById(int id){
        Optional<Student>  studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else {
           throw new RuntimeException("Student not found with id : "+id);
        }
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    /*
     Pagination - fetching or getting the records or data in the form of pages
    pagenumber - the number of page we want to see(0,1,2,3,4,5...)
    pagesize - total number of records in each page(fixed for each page)

    total number of record - 28, page size - 5
    0th page - 1-5
    1st page - 6-10
    2nd page - 11-15
    3rd page - 16-20
    4th page - 21-25
    5th page - 26-28

    total numbers of records-11, page size-3
    0th page - 1-3
    1st page - 4-6
    2nd page - 7-9
    3rd page - 10-11

    on;y pagination
    public List<Student> getStudentByPage(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent();
        return studentList;
    }
     */
    // pagination and sorting
    public List<Student> getStudentByPage(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by("name").ascending())).getContent();
        return studentList;
    }


    public String updateStudent(int studentId, StudentRequestDto newStudentRequestDto){
        Student existingStudent = getStudentById(studentId);
        if(existingStudent!=null){
            existingStudent.setName(newStudentRequestDto.getName());
            existingStudent.setDob(newStudentRequestDto.getDob());
            existingStudent.setDept(newStudentRequestDto.getDept());
            existingStudent.setEmail(newStudentRequestDto.getEmail());
            existingStudent.setGender(newStudentRequestDto.getGender());
            existingStudent.setSem(newStudentRequestDto.getSem());
            existingStudent.setMobile(newStudentRequestDto.getMobile());
            existingStudent.setAddress(newStudentRequestDto.getAddress());

            studentRepository.save(existingStudent);
            return "Student updated successfully";
        } else {
            return "Student with id :"+studentId+" is not found, hence cannot update";
        }
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student with id "+id+" is deleted successfully!";
    }

    public Student getStudentByEmail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        return student;
    }

    public List<Student> getStudentByDept(String dept){
        List<Student> studentList = studentRepository.getStudentByDept(dept);
        return studentList;
    }
}
