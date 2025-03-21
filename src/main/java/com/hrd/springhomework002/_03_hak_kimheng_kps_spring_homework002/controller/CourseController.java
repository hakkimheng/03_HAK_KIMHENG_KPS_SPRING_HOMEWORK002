package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.controller;


import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Course;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.CourseRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.response.ApiResponse;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // get all courses
    @GetMapping
    @Operation(summary = "Get all courses")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer page,
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .timestamp(LocalDateTime.now())
                .message("Get all courses successfully.")
                .status(HttpStatus.OK)
                .payload(courseService.getAllCourses(page, size))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //--End of get all courses

    // get course by id
    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID")
    public ResponseEntity<ApiResponse<Course>> getCourseById(Integer id) {
        Course course = courseService.getCourseById(id);
        ApiResponse<Course> responseFound = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Get course successfully.")
                .status(HttpStatus.OK)
                .payload(course)
                .build();
        ApiResponse<Course> responseNotFound = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Course Id "+id+" not found.")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (course != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of get course by id

    // create course
    @PostMapping
    @Operation(summary = "Create course")
    public ResponseEntity<ApiResponse<Course>> createCourse(CourseRequest courseRequest) {
        Course newCourse = courseService.createCourse(courseRequest);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Create course successfully.")
                .status(HttpStatus.CREATED)
                .payload(newCourse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //--End of create course

    // update course
    @PutMapping("/{id}")
    @Operation(summary = "Update course")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Integer id, CourseRequest courseRequest) {
        Course course = courseService.getCourseById(id);
        ApiResponse<Course> responseFound = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Update course successfully.")
                .status(HttpStatus.OK)
                .payload(courseService.updateCourse(id, courseRequest))
                .build();
        ApiResponse<Course> responseNotFound = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Course Id "+id+" not found.")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (course != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of update course

    // delete course
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable Integer id) {
        Course course = courseService.deleteCourse(id);
        ApiResponse<Course> responseFound = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Delete course successfully.")
                .status(HttpStatus.OK)
                .payload(course)
                .build();
        ApiResponse<Course> responseNotFound = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .message("Course Id "+id+" not found.")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (course != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of delete course

}
