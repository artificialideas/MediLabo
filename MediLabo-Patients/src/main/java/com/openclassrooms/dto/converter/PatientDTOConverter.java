package com.openclassrooms.dto.converter;

import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.model.Patient;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PatientDTOConverter {
    public Patient getEntityFromDTO(PatientDTO dto) {
        Patient entity = null;
        if (dto != null) {
            entity = new Patient();
            entity.setId(dto.getId());
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setBirthdate(dto.getBirthdate());
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
            dto.setId(entity.getId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setBirthdate(entity.getBirthdate());
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
