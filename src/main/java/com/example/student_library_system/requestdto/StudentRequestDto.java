package com.example.student_library_system.requestdto;

import lombok.Data;

@Data
public class StudentRequestDto {

    // request dto(data transfer object) - it is used to take the inputs into APIs.

    private String name;
    private String email;
    private String mobile;
    private String dept;
    private String sem;
    private String gender;
    private String address;
    private String dob;
}
