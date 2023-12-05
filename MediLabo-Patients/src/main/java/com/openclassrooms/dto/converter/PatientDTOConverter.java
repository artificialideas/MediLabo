package com.openclassrooms.dto.converter;

import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.model.Patient;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientDTOConverter {
    private final String datePattern = "yyyy-MM-dd";
    private final DateFormat formattedDate = new SimpleDateFormat(datePattern);

    public Patient getEntityFromDTO(PatientDTO dto) throws ParseException {
        Patient entity = null;
        if (dto != null) {
            entity = new Patient();
            if (dto.getId() != null)
                entity.setId(UUID.fromString(dto.getId()));

            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            if (dto.getBirthdate() != null && !dto.getBirthdate().isEmpty()) {
                entity.setBirthdate(formattedDate.parse(dto.getBirthdate()));
            }
            entity.setGender(dto.getGender());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setAddress(dto.getAddress());
        }

        return entity;
    }

    public PatientDTO getDTOFromEntity(Patient entity) {
        PatientDTO dto = null;
        if (entity != null) {
            dto = new PatientDTO();
            dto.setId(String.valueOf(entity.getId()));

            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            if (entity.getBirthdate() != null) {
                dto.setBirthdate(formattedDate.format(entity.getBirthdate()));
            }
            dto.setGender(entity.getGender());
            dto.setPhoneNumber(entity.getPhoneNumber());
            dto.setAddress(entity.getAddress());
        }

        return dto;
    }

    public List<PatientDTO> getDTOsFromEntities(List<Patient> entities) {
        List<PatientDTO> dtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entities)) {
            for (Patient patient : entities) {
                dtos.add(this.getDTOFromEntity(patient));
            }
        }

        return dtos;
    }
}
