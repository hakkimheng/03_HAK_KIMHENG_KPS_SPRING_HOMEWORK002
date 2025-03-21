package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Student;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer page, Integer size);

    Student getStudentById(Integer id);

  Student createStudent(StudentRequest studentRequest);

    Student updateStudent(Integer id, StudentRequest studentRequest);

    Student deleteStudent(Integer id);
}
