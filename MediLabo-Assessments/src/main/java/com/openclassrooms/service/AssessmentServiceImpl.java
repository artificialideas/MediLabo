package com.openclassrooms.service;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.model.Assessment;
import com.openclassrooms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Autowired
    private PatientService patientService;
    @Autowired
    private NoteService noteService;

    @Override
    public Assessment getPatientRisk(String patId) {
        Optional<Patient> patient = patientService.findById(patId);

        if (patient.isPresent()) {
            List<NoteDTO> patientNotes = noteService.findByPatientId(patId);

            Assessment risk = new Assessment();
            risk.setPatId(patId);

            if (patientNotes.isEmpty()) {
                risk.setStatus(Assessment.Risk.NONE);
            } else {
                int age = getAge(patient.get().getBirthdate());
                List<String> riskKeywords = getKeywords();
                long notesKeywords = patientNotes.stream()
                        .filter(noteDTO -> riskKeywords.contains(noteDTO.getNote()))
                        .count();

                if ((notesKeywords >= 2 && notesKeywords <= 5) && age >= 30) {
                    risk.setStatus(Assessment.Risk.BORDERLINE);
                } else {
                    if ((Objects.equals(patient.get().getGender(), "M") && notesKeywords >= 3) ||
                        (Objects.equals(patient.get().getGender(), "F") && age >= 30 && notesKeywords >= 4)) {
                        risk.setStatus(Assessment.Risk.DANGER);
                    }
                }
            }

            return risk;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with id " + patId + " doesn't exists.");
    }

    List<String> getKeywords() {
        List<String> keywords = new ArrayList<>();
            keywords.add("Hémoglobine A1C");
            keywords.add("Microalbumine");
            keywords.add("Taille");
            keywords.add("Poids");
            keywords.add("Fumeur");
                keywords.add("Fumeuse");
            keywords.add("Anormal");
            keywords.add("Cholestérol");
            keywords.add("Vertiges");
            keywords.add("Rechute");
            keywords.add("Anticorps");
        return keywords;
    }

    int getAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }
}