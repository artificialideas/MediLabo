package com.openclassrooms.controller;

import com.openclassrooms.model.Assessment;
import com.openclassrooms.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/{patId}")
    public Assessment getRisk(
            @PathVariable("patId") String patId) {
        return assessmentService.getPatientRisk(patId);
    }
}
