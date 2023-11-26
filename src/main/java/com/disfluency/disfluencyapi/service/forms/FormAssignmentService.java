package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.repository.FormAssignmentRepo;
import com.disfluency.disfluencyapi.service.notifications.NotificationService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormAssignmentService {

    private final FormAssignmentRepo formAssignmentRepo;
    private final NotificationService notificationService;

    public FormAssignment createExerciseAssignments(Form form){
        return formAssignmentRepo.save(FormAssignment.newFormAssignment(form));
    }

    public FormAssignment getFormAssignmentById(String formAssignmentId) {
        return formAssignmentRepo.findById(formAssignmentId).orElseThrow();
    }

    public List<FormCompletionEntry> getCompletionEntriesByFormAssignmentId(String formAssignmentId) {
        var formAssignment = getFormAssignmentById(formAssignmentId);
        return formAssignment.getCompletionEntries();
    }

    public FormAssignment completeFormAssignment(String formAssignmentId, FormCompletionEntry completionEntry, Patient patient) {
        var formAssignment = getFormAssignmentById(formAssignmentId);
        formAssignment.addCompletionEntry(completionEntry);
        formAssignmentRepo.save(formAssignment);
        try{
            var title = patient.getName() + " ha completado un cuestionario!!";
            var message = "Completo el cuestionario: " + formAssignment.getForm().getTitle() + ". Revisalo para ver como se esta sintiendo.";
            notificationService.sendCommonMessage(title, message, patient.getFcmTokenTherapist());
        }catch (Exception e){
            log.error("Error while notificating: {}",e.toString());
        }
        return formAssignment;
    }
}
