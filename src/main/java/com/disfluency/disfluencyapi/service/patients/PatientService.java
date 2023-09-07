package com.disfluency.disfluencyapi.service.patients;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
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
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final ExerciseService exerciseService;
    private final ExerciseAssignmentService exerciseAssignmentService;
    private final S3Service s3Service;


    public Patient getPatientById(String patientId) {
        return patientRepo.findById(patientId).orElseThrow( () -> new PatientNotFoundException(patientId));
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
        patient.addExercisesAssignment(generateExerciseAssignments());
        return patientRepo.save(patient);
    }

    /**
     * Tempo
     * @return
     */
    private List<ExerciseAssignment> generateExerciseAssignments(){
        var exercises = exerciseService.getAllExercises();
        return exercises.stream().map(exerciseAssignmentService::createExerciseAssignments).toList();
    }

    public PreSignedUrlDTO getPreSignedUrl(String patientId){
        String newUrl = S3_UPLOAD_FOLDER + patientId + LocalDateTime.now() + ".mp3";
        return new PreSignedUrlDTO(s3Service.generatePreSignedUrl(newUrl, S3_BUCKET, HttpMethod.PUT, PRE_SIGNED_UPLOAD_EXPIRATION));
    }

    private Session preSignSessionUrl(Session session){
        String shortUrl = session.getRecordingUrl().replace(S3_BASE_URL, "");
        var preSignedUrl = s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION);
        session.setRecordingUrl(preSignedUrl);
        return session;
    }
}
