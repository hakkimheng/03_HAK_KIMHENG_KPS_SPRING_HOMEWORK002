package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private List<Course> courseList;
}
