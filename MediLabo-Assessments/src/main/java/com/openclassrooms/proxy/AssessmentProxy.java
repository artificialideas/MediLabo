package com.openclassrooms.proxy;

import com.openclassrooms.model.NoteResponse;
import com.openclassrooms.model.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "assessmentClient", url = "http://localhost:9000/")
public interface AssessmentProxy {
    @RequestMapping(method = RequestMethod.GET, value = "patients/api-data/{id}")
    PatientResponse getDataFromServicePatients(
            @PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "notes/api-data/{patId}")
    List<NoteResponse> getDocumentsFromServiceNotes(
            @PathVariable("patId") String patId);
}
