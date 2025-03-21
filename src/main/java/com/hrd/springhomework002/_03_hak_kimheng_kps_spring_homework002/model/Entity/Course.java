package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseDescription;
    private Instructor instructor;

}
