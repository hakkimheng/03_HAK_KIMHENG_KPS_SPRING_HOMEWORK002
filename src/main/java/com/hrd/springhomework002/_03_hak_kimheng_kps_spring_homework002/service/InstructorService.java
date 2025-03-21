package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Instructor;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructor(Integer page , Integer size);

    Instructor getInstructorById(Integer id);

    Instructor createInstructor(InstructorRequest instructorRequest);

    Instructor updateInstructor(Integer id, InstructorRequest instructorRequest);

    Instructor deleteInstructor(Integer id) throws Exception;
}
