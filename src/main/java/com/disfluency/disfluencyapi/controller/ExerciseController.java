package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseAssignmentDTO;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentService;
import com.disfluency.disfluencyapi.service.exercises.ExercisePracticeService;
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final PatientService patientService;
    private final ExerciseAssignmentService exerciseAssignmentService;
    private final ExercisePracticeService exercisePracticeService;

    @GetMapping("exercises/{exerciseId}")
    public Exercise getExerciseById(@PathVariable String exerciseId) {
        return exerciseService.getExerciseById(exerciseId).orElseThrow();
    }

    @GetMapping("exercisesAssignments/{exerciseId}")
    public ExerciseAssignment getExerciseAssignmentById(@PathVariable String exerciseId) {
        return exerciseAssignmentService.getExerciseAssignmentById(exerciseId).orElseThrow();
    }

    // TODO
    @PostMapping(value = "exercisesAssignments/{exerciseId}/practices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExercisePracticeDTO createExercisePractice(@PathVariable String exerciseId, @RequestAttribute("userId") String userId) {
        var patient = patientService.getPatientById(userId);
        return new ExercisePracticeDTO(exerciseAssignmentService.createExercisePractice(exerciseId, patient));
    }

    @GetMapping("exercisesAssignments/{exerciseId}/practices")
    public List<ExercisePractice> getPracticeAttemptsByExerciseAssignmentId(@PathVariable String exerciseId) {
        return exerciseAssignmentService.getExerciseAssignmentById(exerciseId).orElseThrow().getPracticeAttempts();
    }

    //TODO: terminar de probar lo de la presigned a partir de la url normal. La idea es que cuando te traes el objeto del ejercicio desde la base
    // le generes la presigned y pongas eso en el objeto en vez de la url normal
    @GetMapping(value = "/exercisesAssignments/getUrl")
    public ExercisePracticeDTO getAudioUrl(@RequestBody String url) {
        return new ExercisePracticeDTO(exerciseAssignmentService.getPreSignedAudioUrl(url));
    }

    @PostMapping(value = "/exercisesAssignments")
    public void assignExercisesToPatients(@RequestBody NewExerciseAssignmentDTO assignmentDTO){
        exerciseService.assignExercisesToPatients(assignmentDTO, patientService);
    }

    @GetMapping("exercisesPractices/{practiceId}/analysis")
    public Analysis getAnalysisByExercisePracticeId(@PathVariable String practiceId) {
        return exercisePracticeService.getAnalysisByExercisePracticeId(practiceId);
    }
}