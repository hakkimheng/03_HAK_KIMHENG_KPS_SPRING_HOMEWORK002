package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.implementation;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Course;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Student;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.StudentRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository.CourseRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository.StudentRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        return studentRepository.findAllStudents(page, size);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findStudentById(id);
   }

    @Override
   public Student createStudent(StudentRequest studentRequest) {
        List<Course> courseList = courseRepository.findAllCourseNotPagination();
        for (Integer courseId : studentRequest.getCourseId()) {
            if(courseList.stream().noneMatch(course -> course.getCourseId().equals(courseId))){
              return null;
            }
        }
        Student student = studentRepository.insertStudent(studentRequest);
        for (Integer courseId : studentRequest.getCourseId()) {
            studentRepository.insertStudentCourse(student.getStudentId(), courseId);
        }
        return studentRepository.findStudentById(student.getStudentId());
    }

    public Student updateStudent(Integer studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            return null;
        }
        List<Course> courseList = courseRepository.findAllCourseNotPagination();
        for (Integer courseId : studentRequest.getCourseId()) {
            if(courseList.stream().noneMatch(course -> course.getCourseId().equals(courseId))){
                return null;
            }
        }
        studentRepository.updateStudent(studentId, studentRequest);
        studentRepository.deleteStudentCourse(studentId);
        for (Integer courseId : studentRequest.getCourseId()) {
            studentRepository.insertStudentCourse(studentId, courseId);
        }
        return studentRepository.findStudentById(studentId);
    }


    @Override
    public Student deleteStudent(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            return null;
        }
        studentRepository.deleteStudentCourse(id);
        studentRepository.deleteStudent(id);
        return student;
    }


}
