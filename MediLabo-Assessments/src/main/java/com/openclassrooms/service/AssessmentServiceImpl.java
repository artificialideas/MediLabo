package com.openclassrooms.service;

import com.openclassrooms.config.KeywordsConfig;
import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.model.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    private WebClient.Builder webClientBuilder;

    public Mono<PatientDTO> getDataFromServicePatients(String id) {
        String url = "http://localhost:9000/patients/api-data/" + id;

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(PatientDTO.class)
                .next();
    }

    public Mono<List<NoteDTO>> getDocumentsFromServiceNotes(String patId) {
        String url = "http://localhost:9000/notes/api-data/" + patId;

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(NoteDTO.class)
                .collectList();
    }

    @Override
    public Mono<Assessment> getPatientRisk(String patId) {
        // As we are doing http requests to the other services, we need to work with reactive methods
            // Mono -> streams emitting either an error or a single value
        return getDataFromServicePatients(patId)
                // Handle empty result
                .switchIfEmpty(
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with id " + patId + " doesn't exist."))
                )
                .flatMap(patientDTO -> getDocumentsFromServiceNotes(patId)
                        .flatMapMany(Flux::fromIterable)
                        .collectList()
                        .map(patientNotes -> {
                            try {
                                return calculateRisk(patientDTO, patientNotes);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                );
    }

    private Assessment calculateRisk(PatientDTO patientDTO, List<NoteDTO> patientNotes) throws IOException {
        Assessment risk = new Assessment();
        risk.setPatId(patientDTO.getId());

        int age = getAge(LocalDate.parse(patientDTO.getBirthdate()));
        List<String> riskKeywords = getKeywords();
        long notesKeywords = patientNotes.stream()
                .filter(noteDTO -> riskKeywords.contains(noteDTO.getNote()))
                .count();

        if (patientNotes.isEmpty()) {
            risk.setStatus(Assessment.Risk.NONE);
        } else if (notesKeywords >= 2 && notesKeywords <= 5 && age >= 30) {
            risk.setStatus(Assessment.Risk.BORDERLINE);
        } else if (isDangerCondition(patientDTO, age, notesKeywords)) {
            risk.setStatus(Assessment.Risk.DANGER);
        } else if (isEarlyOnsetCondition(patientDTO, age, notesKeywords)) {
            risk.setStatus(Assessment.Risk.EARLY_ONSET);
        }

        return risk;
    }

    private boolean isDangerCondition(PatientDTO patientDTO, int age, long notesKeywords) {
        return (Objects.equals(patientDTO.getGender(), "M") && age <= 30 && notesKeywords == 3) ||
                (Objects.equals(patientDTO.getGender(), "F") && age <= 30 && notesKeywords == 4) ||
                (age > 30 && (notesKeywords == 6 || notesKeywords == 7));
    }

    private boolean isEarlyOnsetCondition(PatientDTO patientDTO, int age, long notesKeywords) {
        return (Objects.equals(patientDTO.getGender(), "M") && age <= 30 && notesKeywords >= 5) ||
                (Objects.equals(patientDTO.getGender(), "F") && age <= 30 && notesKeywords >= 7) ||
                (age > 30 && notesKeywords >= 8);
    }

    private List<String> getKeywords() throws IOException {
        return keywordsConfig.getRiskKeywords();
    }

    int getAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }
}