package com.openclassrooms.dto.converter;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteDTOConverter {
    public Note getEntityFromDTO(NoteDTO dto) {
        Note entity = null;
        if (dto != null) {
            entity = new Note();
            ObjectId objectId = new ObjectId(dto.getId());
            entity.setId(objectId);

            entity.setPatId(UUID.fromString(dto.getPatId()));
            entity.setPatient(dto.getPatient());
            entity.setNote(dto.getNote());
        }

        return entity;
    }

    public NoteDTO getDTOFromEntity(Note entity) {
        NoteDTO dto = null;
        if (entity != null) {
            dto = new NoteDTO();
            dto.setId(String.valueOf(entity.getId()));

            dto.setPatId(String.valueOf(entity.getPatId()));
            dto.setPatient(entity.getPatient());
            dto.setNote(entity.getNote());
        }

        return dto;
    }

    public List<NoteDTO> getDTOsFromEntities(List<Note> entities) {
        List<NoteDTO> dtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entities)) {
            for (Note note : entities) {
                dtos.add(this.getDTOFromEntity(note));
            }
        }

        return dtos;
    }
}
