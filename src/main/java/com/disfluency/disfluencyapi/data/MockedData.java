package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.domain.forms.*;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.repository.*;

import java.util.List;

public class MockedData {

    private MockedData(){}

    public static void saveData(PatientRepo patientRepo, TherapistRepo therapistRepo, ExerciseRepo exerciseRepo, UserRepo userRepo, ExerciseAssignmentsRepo exerciseAssignmentsRepo, ExercisePracticeRepo exercisePracticeRepo, FormRepo formRepo, FormQuestionRepo formQuestionRepo, FormAssignmentRepo formAssignmentRepo, FormQuestionResponseRepo formQuestionResponseRepo, FormCompletionEntryRepo formCompletionEntryRepo){
        List<User> userList = MockedUser.all;
        List<Patient> patientList = MockedPatient.all;
        List<Therapist> therapistList = MockedTherapist.all;
        List<Exercise> exerciseList = MockedExercise.all;
        List<FormQuestion> questionList = MockedForm.all.stream().flatMap(f -> f.getQuestions().stream()).toList();
        List<Form> formList = MockedForm.all;
        List<FormAssignment> formAssignmentList = MockedPatient.all.stream().flatMap(p -> p.getFormAssignments().stream()).toList();
        List<FormCompletionEntry> formCompletionList = formAssignmentList.stream().flatMap(a -> a.getCompletionEntries().stream()).toList();
        List<FormQuestionResponse> questionResponseList = formCompletionList.stream().flatMap(c -> c.getResponses().stream()).toList();
        List<ExerciseAssignment> exerciseAssignmentList = MockedPatient.all.stream().flatMap(p -> p.getExerciseAssignments().stream()).toList();
        List<ExercisePractice> exercisePracticeList = exerciseAssignmentList.stream().flatMap(a -> a.getPracticeAttempts().stream()).toList();

        // Adding all to database
        formQuestionRepo.saveAll(questionList);
        formRepo.saveAll(formList);
        formAssignmentRepo.saveAll(formAssignmentList);
        formQuestionResponseRepo.saveAll(questionResponseList);
        formCompletionEntryRepo.saveAll(formCompletionList);
        exerciseRepo.saveAll(exerciseList);
        exercisePracticeRepo.saveAll(exercisePracticeList);
        exerciseAssignmentsRepo.saveAll(exerciseAssignmentList);
        patientRepo.saveAll(patientList);
        therapistRepo.saveAll(therapistList);
        userRepo.saveAll(userList);
    }

    public static void drop(PatientRepo patientRepo, TherapistRepo therapistRepo, ExerciseRepo exerciseRepo, UserRepo userRepo, ExerciseAssignmentsRepo exerciseAssignmentsRepo, ExercisePracticeRepo exercisePracticeRepo, FormRepo formRepo, FormQuestionRepo formQuestionRepo, FormAssignmentRepo formAssignmentRepo, FormQuestionResponseRepo formQuestionResponseRepo, FormCompletionEntryRepo formCompletionEntryRepo){
        formQuestionResponseRepo.deleteAll();
        formCompletionEntryRepo.deleteAll();
        formAssignmentRepo.deleteAll();
        formRepo.deleteAll();
        formQuestionRepo.deleteAll();
        patientRepo.deleteAll();
        exerciseRepo.deleteAll();
        therapistRepo.deleteAll();
        userRepo.deleteAll();
        exerciseAssignmentsRepo.deleteAll();
        exercisePracticeRepo.deleteAll();
    }
}
