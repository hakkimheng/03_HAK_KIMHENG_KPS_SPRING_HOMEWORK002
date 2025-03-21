package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Course;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Student;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.StudentRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("""
 SELECT * FROM students
     OFFSET #{size} * (#{page} - 1)
     LIMIT #{size}
 """)
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "studentEmail", column = "student_email"),
            @Result(property = "studentPhone", column = "student_phone"),
            @Result(property = "courseList", column = "student_id",
                    many = @Many(select = "findCourseByStudentId"))
    })
    List<Student> findAllStudents(Integer page, Integer size);

    // -- find course by student id
    @Select("""
            SELECT * from courses
        INNER JOIN student_course sc ON courses.course_id = sc.course_id
        WHERE sc.student_id = #{id}
        """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository.InstructorRepository.findInstructorById"))
    })
    List<Course> findCourseByStudentId(Integer
id);



    // -- find student by id
    @Select("""
       SELECT * FROM students
       WHERE student_id = #{id}
    """)
    @ResultMap("studentMapper")
    Student findStudentById(Integer id);

    @Select("""
        INSERT INTO students(student_name, student_email, student_phone )
        VALUES (#{student.studentName}, #{student.studentEmail}, #{student.studentPhone})
        RETURNING *;
    """)
    @ResultMap("studentMapper")
    Student insertStudent(@Param("student") StudentRequest studentRequest);

    @Select("""
        INSERT INTO student_course(student_id, course_id)
        VALUES (#{studentId}, #{courseId})
        RETURNING *;
    """)
    @ResultMap("courseMapper")
    void insertStudentCourse(Integer studentId , Integer courseId);

    // -- update student
    @Select("""
        UPDATE students SET student_name = #{student.studentName}, student_email = #{student.studentEmail}, student_phone = #{student.studentPhone}
        WHERE student_id = #{id};
    """)
    @ResultMap("studentMapper")
    void updateStudent(Integer id, @Param("student") StudentRequest studentRequest);

    // -- delete student course
    @Select("""
        DELETE FROM student_course
        WHERE student_id = #{studentId}
    """)
    void deleteStudentCourse(Integer studentId);


    // -- delete student
    @Select("""
        DELETE FROM students
        WHERE student_id = #{id}
    """)
    void deleteStudent(Integer id);
}
