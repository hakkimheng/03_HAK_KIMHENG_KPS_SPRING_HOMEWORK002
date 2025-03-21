package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.controller;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Student;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.StudentRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.response.ApiResponse;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.jar.JarOutputStream;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // get all students
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Student> studentResponses = studentService.getAllStudents(page, size);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .timestamp(LocalDateTime.now())
                .message("Get all students successfully")
                .status(HttpStatus.OK)
                .payload(studentResponses)
                .build();
        return ResponseEntity.ok(response);
    }
    //--End of get all students

    // get student by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(Integer id) {
        Student student = studentService.getStudentById(id);
        ApiResponse<Student> responseFound = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Get student successfully.")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        ApiResponse<Student> responseNotFound = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Student Id "+id+" not found.")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of get student by id

    // create student
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest) {
        Student newStudent = studentService.createStudent(studentRequest);
        System.out.println(studentRequest.getCourseId());
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Create student successfully.")
                .status(HttpStatus.CREATED)
                .payload(newStudent)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //--End of create student

    // update student
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {
        Student student = studentService.updateStudent(id, studentRequest);
        ApiResponse<Student> responseFound = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Update student successfully.")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        ApiResponse<Student> responseNotFound = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Student Id "+id+" not found.")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of update student


    // delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudent(@PathVariable Integer id) {
        Student student = studentService.deleteStudent(id);
        ApiResponse<Student> responseFound = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Delete student successfully.")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        ApiResponse<Student> responseNotFound = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Student Id "+id+" not found.")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
}
