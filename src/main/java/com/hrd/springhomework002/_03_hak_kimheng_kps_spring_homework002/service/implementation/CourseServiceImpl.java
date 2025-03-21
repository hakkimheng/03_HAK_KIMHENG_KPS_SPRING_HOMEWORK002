package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.implementation;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Course;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.CourseRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository.CourseRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Course> getAllCourses(Integer page , Integer size) {
        return courseRepository.findAllCourse(page, size);
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        return courseRepository.insertCourse(courseRequest);
    }

    @Override
    public Course updateCourse(Integer id, CourseRequest courseRequest) {
        return courseRepository.updateCourse(id, courseRequest);
    }

    @Override
    public Course deleteCourse(Integer id) {
        return courseRepository.deleteCourse(id);
    }

}
