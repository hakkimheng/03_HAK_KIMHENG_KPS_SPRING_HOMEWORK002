package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.controller;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Instructor;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.InstructorRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.response.ApiResponse;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    //get all instructors
    @GetMapping
    @Operation(summary = "Get all Instructors")
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllAuthors(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Instructor> instructorList = instructorService.getAllInstructor(page, size);
        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .timestamp(LocalDateTime.now())
                .message("Get all authors successfully.")
                .status(HttpStatus.OK)
                .payload(instructorList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //--End of get all instructors

    //get instructor by id
    @GetMapping("/{id}")
    @Operation(summary = "Get Instructor by ID")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Integer id) {
        Instructor instructor = instructorService.getInstructorById(id);
        ApiResponse<Instructor> responseFound = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Get instructor successfully")
                .status(HttpStatus.OK)
                .payload(instructor)
                .build();
        ApiResponse<Instructor> responseNotFound = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Instructor not found")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (instructor != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of get instructor by id

    //create instructor
    @PostMapping
    @Operation(summary = "Create Instructor")
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorRequest instructorRequest) {
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Instructor created successfully")
                .status(HttpStatus.CREATED)
                .payload(instructorService.createInstructor(instructorRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //--End of create instructor

    //update instructor
    @PutMapping("/{id}")
    @Operation(summary = "Update Instructor")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Integer id, @RequestBody InstructorRequest instructorRequest) {
        Instructor instructor = instructorService.updateInstructor(id, instructorRequest);
        ApiResponse<Instructor> responseFound = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Instructor updated successfully")
                .status(HttpStatus.OK)
                .payload(instructor)
                .build();
        ApiResponse<Instructor> responseNotFound = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Instructor ID "+ id +" not found")
                .status(HttpStatus.NOT_FOUND)
                .payload(null)
                .build();
        if (instructor != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFound);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }
    }
    //--End of update instructor

    //delete instructor
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Instructor")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Integer id)  {

        try {
            Instructor instructor = instructorService.deleteInstructor(id);
            ApiResponse<Instructor> responseFound = ApiResponse.<Instructor>builder()
                    .timestamp(LocalDateTime.now())
                    .message("Instructor deleted successfully")
                    .status(HttpStatus.OK)
                    .payload(instructor)
                    .build();
            ApiResponse<Instructor> responseNotFound = ApiResponse.<Instructor>builder()
                    .timestamp(LocalDateTime.now())
                    .message("Instructor ID "+ id +" not found")
                    .status(HttpStatus.NOT_FOUND)
                    .payload(null)
                    .build();
            if (instructor != null) {
                return ResponseEntity.status(HttpStatus.OK).body(responseFound);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
            }
        } catch (Exception e) {
            ApiResponse<Instructor> instructor = ApiResponse.<Instructor>builder()
                    .timestamp(LocalDateTime.now())
                    .message("Instructor ID "+ id +" Have a course ! Can't delete")
                    .status(HttpStatus.BAD_REQUEST)
                    .payload(null)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(instructor);

        }



    }

}
