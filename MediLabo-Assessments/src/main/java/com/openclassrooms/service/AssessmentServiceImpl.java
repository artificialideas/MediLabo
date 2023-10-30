package com.openclassrooms.service;

import com.openclassrooms.dao.AssessmentRepository;
import com.openclassrooms.model.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Override
    public List<Assessment> findAll () {
        return assessmentRepository.findAll();
    }
}
