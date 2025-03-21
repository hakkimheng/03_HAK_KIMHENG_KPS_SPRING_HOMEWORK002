package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Course;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer page, Integer size);

    Course getCourseById(Integer id);

    Course createCourse(CourseRequest courseRequest);

    Course updateCourse(Integer id, CourseRequest courseRequest);

    Course deleteCourse(Integer id);
}
