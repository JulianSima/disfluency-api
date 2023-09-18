package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.sessions.SessionTurn;
import com.disfluency.disfluencyapi.domain.state.PatientUserState;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.disfluency.disfluencyapi.data.MockedUser.PROFILE_PICTURES;

public class MockedPatient {
    static final Patient dibu;
    static final Patient messi;
    static final Patient depaul;
    static final Patient paredes;

    static final List<Patient> all;

    private MockedPatient(){}

    static {
        dibu = Patient.builder()
                .name("Emiliano")
                .lastName("Martinez")
                .email("tecomo@gmail.com")
                .dateOfBirth(LocalDate.now().minusYears(32))
                .sessionTurn(new SessionTurn(List.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY), LocalTime.of(15, 30)))
                .profilePictureUrl(PROFILE_PICTURES[2])
                .state(PatientUserState.ACTIVE)
                .exerciseAssignments(
                    MockedExercise.allAssignments()
                )
                .formAssignments(
                    MockedForm.allAssignments()
                )
                .build();

        messi = Patient.builder()
                .name("Lionel Andrés")
                .lastName("Messi")
                .email("lio@gmail.com")
                .dateOfBirth(LocalDate.now().minusYears(36))
                .sessionTurn(new SessionTurn(List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), LocalTime.of(15, 30)))
                .profilePictureUrl(PROFILE_PICTURES[1])
                .state(PatientUserState.ACTIVE)
                .exerciseAssignments(
                    List.of(
                        ExerciseAssignment.builder()
                            .exercise(MockedExercise.inicioSuave)
                            .dateOfAssignment(LocalDate.now())
                            .practiceAttempts(
                                List.of(
                                    ExercisePractice.newExercisePractice("https://pf5302.s3.us-east-2.amazonaws.com/audios/iniciosuave.mp3")
                                )
                            )
                            .build(),
                        ExerciseAssignment.newExerciseAssignment(MockedExercise.toquesLigeros),
                        ExerciseAssignment.newExerciseAssignment(MockedExercise.fonacionContinuada),
                        ExerciseAssignment.newExerciseAssignment(MockedExercise.velocidadComoda)
                    )
                )
                .formAssignments(
                    MockedForm.allAssignments()
                )
                .build();

        depaul = Patient.builder()
                .name("Rodrigo")
                .lastName("De Paul")
                .email("rodri@gmail.com")
                .dateOfBirth(LocalDate.now().minusYears(28))
                .sessionTurn(new SessionTurn(List.of(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY), LocalTime.of(15, 30)))
                .profilePictureUrl(PROFILE_PICTURES[3])
                .state(PatientUserState.ACTIVE)
                .exerciseAssignments(
                    List.of(
                        ExerciseAssignment.builder()
                            .exercise(MockedExercise.toquesLigeros)
                            .dateOfAssignment(LocalDate.now())
                            .practiceAttempts(
                                List.of(
                                    ExercisePractice.newExercisePractice("https://pf5302.s3.us-east-2.amazonaws.com/audios/toquesligeros.mp3")
                                )
                            )
                            .build(),
                        ExerciseAssignment.newExerciseAssignment(MockedExercise.inicioSuave),
                        ExerciseAssignment.newExerciseAssignment(MockedExercise.fonacionContinuada),
                        ExerciseAssignment.newExerciseAssignment(MockedExercise.velocidadComoda)
                    )
                )
                .formAssignments(
                    MockedForm.allAssignments()
                )
                .build();

        paredes = Patient.builder()
                .name("Leandro")
                .lastName("Paredes")
                .email("paredes@gmail.com")
                .dateOfBirth(LocalDate.now().minusYears(29))
                .sessionTurn(new SessionTurn(List.of(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY), LocalTime.of(15, 30)))
                .profilePictureUrl(PROFILE_PICTURES[5])
                .state(PatientUserState.ACTIVE)
                .exerciseAssignments(
                    MockedExercise.allAssignments()
                )
                .formAssignments(
                    MockedForm.allAssignments()
                )
                .build();

        all = List.of(dibu, messi, depaul, paredes);
    }
}
