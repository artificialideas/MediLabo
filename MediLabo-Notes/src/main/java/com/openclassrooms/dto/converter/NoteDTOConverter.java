package com.openclassrooms.dto.converter;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteDTOConverter {
    private final String datePattern = "yyyy-MM-dd";
    private final DateFormat formattedDate = new SimpleDateFormat(datePattern);
    public Note getEntityFromDTO(NoteDTO dto) throws ParseException {
        Note entity = null;
        if (dto != null) {
            entity = new Note();
            ObjectId objectId = new ObjectId(dto.getId());
            entity.setId(objectId);

            if (dto.getDate() != null && !dto.getDate().isEmpty()) {
                entity.setDate(formattedDate.parse(dto.getDate()));
            }
            entity.setPatId(dto.getPatId());
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

            dto.setDate(String.valueOf(entity.getDate()));
            dto.setPatId(entity.getPatId());
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
