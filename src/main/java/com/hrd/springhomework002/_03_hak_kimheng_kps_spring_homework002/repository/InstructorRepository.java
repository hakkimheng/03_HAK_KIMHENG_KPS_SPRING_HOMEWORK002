package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Instructor;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.InstructorRequest;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface InstructorRepository {

    // -- find all instructor
    @Select("""
    select * from instructors
    offset  #{size} * (#{page} -1)
    limit #{size}
""")
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "instructorEmail", column = "instructor_email")
    })
    List<Instructor> findAllInstructor(Integer page, Integer size);
    //-- end of find all instructor

    // -- find instructor by id
    @Select("""
    select * from instructors
    where instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    Instructor findInstructorById(Integer id);
    //-- end of find instructor by id

    // -- insert instructor
    @Select(("""
    insert into instructors(instructor_name, instructor_email)
    values (#{instructor.instructorName}, #{instructor.instructorEmail})
    RETURNING *;
    """))
    @ResultMap("instructorMapper")
    Instructor insertInstructor(@Param("instructor") InstructorRequest instructorRequest);
    //-- end of insert instructor

    // -- update instructor
    @Select("""
    update instructors set instructor_name = #{instructor.instructorName}, instructor_email = #{instructor.instructorEmail}
    where instructor_id = #{id}
    RETURNING *;
    """)
    @ResultMap("instructorMapper")
    Instructor updateInstructor(Integer id, @Param("instructor") InstructorRequest instructorRequest);
    //-- end of update instructor

    // -- delete instructor
    @Select("""
    delete from instructors
    where instructor_id = #{id}
    RETURNING *;
    """)
    @ResultMap("instructorMapper")
    Instructor deleteInstructor(Integer id);
}
    //-- end of delete instructor


