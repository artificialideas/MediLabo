package com.openclassrooms.service;

import com.openclassrooms.config.KeywordsConfig;
import com.openclassrooms.model.Assessment;
import com.openclassrooms.model.NoteResponse;
import com.openclassrooms.model.PatientResponse;
import com.openclassrooms.proxy.AssessmentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Autowired
    private KeywordsConfig keywordsConfig;

    @Autowired
    private AssessmentProxy assessmentProxy;

    @Override
    public Assessment getPatientRisk(String patId) {
        PatientResponse patientResponse = assessmentProxy.getDataFromServicePatients(patId);

        if (patientResponse != null) {
            List<NoteResponse> noteResponseList = assessmentProxy.getDocumentsFromServiceNotes(patId);
            if (noteResponseList != null) {
                try {
                    return calculateRisk(patientResponse, noteResponseList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error fetching notes for patient patient " + patId + ".");
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with id " + patId + " doesn't exist.");
    }

    private Assessment calculateRisk(PatientResponse patientResponse, List<NoteResponse> patientNotes) throws IOException {
        Assessment risk = new Assessment();
        risk.setPatId(patientResponse.getId());

        int age = getAge(LocalDate.parse(patientResponse.getBirthdate()));
        List<String> riskKeywords = getKeywords();
        long notesKeywords = patientNotes.stream()
                .flatMap(noteDTO -> riskKeywords.stream()
                        .filter(keyword -> noteDTO.getNote().contains(keyword)))
                .count();

        if (patientNotes.isEmpty()) {
            risk.setStatus(Assessment.Risk.NONE);
        } else if (notesKeywords >= 2 && notesKeywords <= 5 && age >= 30) {
            risk.setStatus(Assessment.Risk.BORDERLINE);
        } else if (isDangerCondition(patientResponse, age, notesKeywords)) {
            risk.setStatus(Assessment.Risk.DANGER);
        } else if (isEarlyOnsetCondition(patientResponse, age, notesKeywords)) {
            risk.setStatus(Assessment.Risk.EARLY_ONSET);
        } else
            risk.setStatus(Assessment.Risk.NONE);

        return risk;
    }

    int getAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }

    private List<String> getKeywords() throws IOException {
        return keywordsConfig.getRiskKeywords();
    }

    private boolean isDangerCondition(PatientResponse patientResponse, int age, long notesKeywords) {
        return (Objects.equals(patientResponse.getGender(), "M") && age <= 30 && notesKeywords == 3) ||
                (Objects.equals(patientResponse.getGender(), "F") && age <= 30 && notesKeywords == 4) ||
                (age > 30 && (notesKeywords == 6 || notesKeywords == 7));
    }

    private boolean isEarlyOnsetCondition(PatientResponse patientResponse, int age, long notesKeywords) {
        return (Objects.equals(patientResponse.getGender(), "M") && age <= 30 && notesKeywords >= 5) ||
                (Objects.equals(patientResponse.getGender(), "F") && age <= 30 && notesKeywords >= 7) ||
                (age > 30 && notesKeywords >= 8);
    }
}