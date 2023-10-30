package com.openclassrooms.controller;

import com.openclassrooms.model.Assessment;
import com.openclassrooms.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/assessments")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/")
    public List<Assessment> getAssessments() {
        return assessmentService.findAll();
    }
}
