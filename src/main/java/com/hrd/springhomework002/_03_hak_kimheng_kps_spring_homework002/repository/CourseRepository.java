package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Course;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.CourseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("""
        SELECT * FROM courses
        offset  #{size} * (#{page} -1)
        limit #{size}
    """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository.InstructorRepository.findInstructorById"))
    })
    List<Course> findAllCourse(Integer page, Integer size);

    @Select("""
        SELECT * FROM courses
        WHERE course_id = #{id}
    """)
    @ResultMap("courseMapper")
    Course findCourseById(Integer id);

    // find all course not pagination
    @Select("""
        SELECT * FROM courses
    """)
    @ResultMap("courseMapper")
    List<Course> findAllCourseNotPagination();

   @Select("""
        INSERT INTO courses(course_name, course_description, instructor_id)
        VALUES (#{course.courseName}, #{course.courseDescription}, #{course.instructorId})
        RETURNING *;
    """)
    @ResultMap("courseMapper")
    Course insertCourse(@Param("course") CourseRequest courseRequest);


    @Select("""
        UPDATE courses SET course_name = #{course.courseName}, course_description = #{course.courseDescription}, instructor_id = #{course.instructorId}
        WHERE course_id = #{id}
        RETURNING *;
    """)
    Course updateCourse(Integer id,@Param("course") CourseRequest courseRequest);


    @Select("""
        DELETE FROM courses
        WHERE course_id = #{id}
        RETURNING *;
    """)
    @ResultMap("courseMapper")
    Course deleteCourse(Integer id);
}
