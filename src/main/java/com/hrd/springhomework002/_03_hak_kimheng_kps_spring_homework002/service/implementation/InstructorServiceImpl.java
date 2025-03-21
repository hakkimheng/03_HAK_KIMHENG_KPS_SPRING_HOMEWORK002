package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.implementation;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity.Instructor;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.request.InstructorRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.repository.InstructorRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructor(Integer page, Integer size) {
        return instructorRepository.findAllInstructor(page, size);
    }

    @Override
    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findInstructorById(id);
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor updateInstructor(Integer id, InstructorRequest instructorRequest) {
        return instructorRepository.updateInstructor(id, instructorRequest);
    }

    @Override
    public Instructor deleteInstructor(Integer id) throws Exception {

        return instructorRepository.deleteInstructor(id);
    }
}
