package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String courseName;
    private String courseDescription;
    private Integer instructorId;
}
