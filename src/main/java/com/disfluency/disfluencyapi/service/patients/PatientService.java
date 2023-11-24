package com.disfluency.disfluencyapi.service.patients;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.domain.state.PatientUserState;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.patients.PreSignedUrlDTO;
import com.disfluency.disfluencyapi.dto.session.NewSessionDTO;
import com.disfluency.disfluencyapi.exception.PatientNotFoundException;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.service.analysis.AnalysisService;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentService;
import com.disfluency.disfluencyapi.service.forms.FormAssignmentService;
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import com.disfluency.disfluencyapi.service.forms.FormService;
import com.disfluency.disfluencyapi.service.notifications.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.disfluency.disfluencyapi.service.aws.S3Service.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    
    private final PatientRepo patientRepo;
    private final AnalysisService analysisService;
    private final ExerciseAssignmentService exerciseAssignmentService;
    private final FormAssignmentService formAssignmentService;
    private final S3Service s3Service;
    private final NotificationService notificationService;


    public Patient getPatientById(String patientId) {
        return patientRepo.findById(patientId).orElseThrow( () -> new PatientNotFoundException(patientId));
    }

    public List<Patient> getPatientsByIdList(List<String> ids) {
        return patientRepo.findAllById(ids);
    }

    public Patient createPatient(NewPatientDTO newPatient) {
        return patientRepo.save(Patient.newPatient(newPatient));
    }


    public Session createTherapySessionForPatient(NewSessionDTO newSession, String patientId) {
        var patient = getPatientById(patientId);
        var url = newSession.recordingUrl();

        String shortUrl = url
                .substring(0, url.indexOf("?"))
                .replace(S3_BASE_URL, "")
                .replace("%3A", ":");

        var preSignedUrl = s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION);
        var session = analysisService.createAnalysedSession(S3_BASE_URL + shortUrl, preSignedUrl);
        patient.addTherapySession(session);
        patientRepo.save(patient);
        return session;
    }

    public List<Session> getTherapySessionsForPatient(String patientId) {
        var patient = getPatientById(patientId);
        return patient.getTherapySession().stream().map(this::preSignSessionUrl).toList();
    }

    public Patient presignPatientUrls(Patient patient) {
        patient.getExerciseAssignments().forEach(exerciseAssignmentService::presignExerciseUrls);
        return patient;
    }
  
    public Patient confirmPatient(Patient patient){
        patient.setState(PatientUserState.ACTIVE);
//        patient.addExercisesAssignment(generateExerciseAssignments());
//        patient.addFormsAssignment(generateFormAssignments());
        return patientRepo.save(patient);
    }

    public void formAssignments(String patientId, List<Form> forms) {
        var patient = getPatientById(patientId);

        List<FormAssignment> formAssignments = forms.stream()
                .map(formAssignmentService::createExerciseAssignments)
                .toList();
        patient.addFormsAssignment(formAssignments);
        patientRepo.save(patient);
        try{
            notificationService.sendCommonMessage("A trabajar", "Tu terapeuta te ha asignado cuestionarios para practicar.", patient.getFcmToken());
        }catch (Exception e){
            log.error("Error while notificating: {}",e.toString());
        }
    }


    public PreSignedUrlDTO getPreSignedUrl(String patientId){
        String newUrl = S3_UPLOAD_FOLDER + patientId + LocalDateTime.now() + ".mp3";
        return new PreSignedUrlDTO(s3Service.generatePreSignedUrl(newUrl, S3_BUCKET, HttpMethod.PUT, PRE_SIGNED_UPLOAD_EXPIRATION));
    }

    private Session preSignSessionUrl(Session session){
        String shortUrl = session.getAnalysis().getRecordingUrl().replace(S3_BASE_URL, "");
        var preSignedUrl = s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION);
        session.getAnalysis().setRecordingUrl(preSignedUrl);
        return session;
    }

    public void assignExercisesToPatient(String patientId, List<Exercise> exercises) {
        var patient = getPatientById(patientId);

        List<ExerciseAssignment> exerciseAssignments = exercises.stream()
                .map(exerciseAssignmentService::createExerciseAssignments)
                .toList();

        patient.addExercisesAssignment(exerciseAssignments);
        patientRepo.save(patient);
        try{
            notificationService.sendCommonMessage("A trabajar", "Tu terapeuta te ha asignado ejercicios para practicar.", patient.getFcmToken());
        }catch (Exception e){
            log.error("Error while notificating: {}",e.toString());
        }
    }

    public void setFcmToken(Patient patient, String fcmToken) {
        patient.setFcmToken(fcmToken);
        patientRepo.save(patient);
    }
}
