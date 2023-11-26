package com.disfluency.disfluencyapi.service.exercises;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.repository.ExerciseAssignmentsRepo;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import com.disfluency.disfluencyapi.service.notifications.NotificationService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.disfluency.disfluencyapi.service.aws.S3Service.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseAssignmentService {

    private final ExerciseAssignmentsRepo exerciseAssignmentsRepo;
    private final ExercisePracticeService exercisePracticeService;
    private final NotificationService notificationService;
    private final S3Service s3Service;

    public ExerciseAssignment createExerciseAssignments(Exercise exercise){
        return exerciseAssignmentsRepo.save(ExerciseAssignment.newExerciseAssignment(exercise));
    }

    public Optional<ExerciseAssignment> getExerciseAssignmentById(String exerciseId) {
        return exerciseAssignmentsRepo.findById(exerciseId);
    }

    public String createExercisePractice(String exerciseId, Patient patient) {
        var exerciseAssignment = exerciseAssignmentsRepo.findById(exerciseId).orElseThrow();
        String url = S3_UPLOAD_FOLDER + exerciseId + LocalDateTime.now() + ".mp3";
        var preSignedUrl = s3Service.generatePreSignedUrl(url, S3_BUCKET, HttpMethod.PUT, PRE_SIGNED_UPLOAD_EXPIRATION);
        log.info(preSignedUrl);

        exerciseAssignment.addExercisePractice(exercisePracticeService.createExercisePractice(S3_BASE_URL + url));
        log.info(exerciseAssignment.toString());
        exerciseAssignmentsRepo.save(exerciseAssignment);
        try{
            var title = patient.getName() + " " + patient.getLastName() + " ha practicado un ejercicio!!";
            var message = "Practico " + exerciseAssignment.getExercise().getTitle() + ". Revisalo para ver la evolucion de tu paciente";
            notificationService.sendCommonMessage(title, message, patient.getFcmTokenTherapist());
        }catch (Exception e){
            log.error("Error while notificating: {}",e.toString());
        }
        return preSignedUrl;
    }

    public String getPreSignedAudioUrl(String url){
        String shortUrl = url.replace(S3_BASE_URL, "");
        return s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION);
    }

    public void presignExerciseUrls(ExerciseAssignment exerciseAssignment) {
        presignUrl(exerciseAssignment.getExercise());
        exerciseAssignment.getPracticeAttempts().forEach(exercisePracticeService::presignUrl);
    }

    public void presignUrl(Exercise exercise) {
        String shortUrl = exercise.getSampleRecordingUrl().replace(S3_BASE_URL, "");
        exercise.setSampleRecordingUrl(
                s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION)
        );
    }
}