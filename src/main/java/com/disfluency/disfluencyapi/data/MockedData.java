package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.repository.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockedData {

    public static void saveData(PatientRepo patientRepo, TherapistRepo therapistRepo, ExerciseRepo exerciseRepo, UserRepo userRepo, ExerciseAssignmentsRepo exerciseAssignmentsRepo, ExercisePracticeRepo exercisePracticeRepo){
        List<Patient> patientList = new ArrayList<>();
        List<Therapist> therapistList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Exercise> exerciseList = new ArrayList<>();
        List<ExerciseAssignment> exerciseAssignmentList = new ArrayList<>();
        List<ExercisePractice> exercisePracticeList = new ArrayList<>();

        // Creating exercises
        Exercise fonacionContinuada = Exercise.newExercise(new NewExerciseDTO(
                "Fonación continuada",
                "Mantener la fonacion a lo largo de la palabra y entre palabra y palabra. Mantener la vibracion de las cuerdas vocales a lo largo de la palabra y entre palabras sin frenar, y sostener durante toda una frase",
                "La portabilidad es la capacidad del producto o componente de ser transferido de forma efectiva y eficiente de un entorno hardware, software, operacional o de utilización a otro",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/fonacion.mp3")
        );
        exerciseList.add(fonacionContinuada);

        Exercise inicioSuave = Exercise.newExercise(new NewExerciseDTO(
                "Inicio suave",
                "Es un suave comienzo en la vibración de las cuerdas vocales en el inicio de cualquier palabra que comience con vocal. Dejo salir un poco de aire a través de las cuerdas vocales antes de comenzar la fonación; quizás un poco aireado al principio pero mejorará con la práctica",
                "La fiabilidad es la capacidad de un sistema o componente para desempeñar las funciones especificadas, cuando se usa bajo unas condiciones y periodo de tiempo determinados",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/iniciosuave.mp3")
        );
        exerciseList.add(inicioSuave);

        Exercise toquesLigeros = Exercise.newExercise(new NewExerciseDTO(
                "Toques ligeros",
                "Producir las consonantes con movimientos y toques suaves entre las zonas de contacto. Empiezo todas las palabras que comienzan con consonante con una suave producción; toco y me voy; evito la presión en el resto de las consonantes de la palabra",
                "La mantenibilidad es la característica que representa la capacidad del producto software para ser modificado efectiva y eficientemente, debido a necesidades evolutivas, correctivas o perfectivas",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/toquesligeros.mp3")
        );
        exerciseList.add(toquesLigeros);

        Exercise velocidadComoda = Exercise.newExercise(new NewExerciseDTO(
                "Velocidad cómoda y fluida",
                "Controlar la velocidad de manera que me sea cómodo, tratar de mantenerla ajustándola a mi comodidad. Hablá a una velocidad cómoda y constante a lo largo de las palabras; y de las frases; bajá un poco la velocidad cuando notás un poco de tensión en tu máquina de hablar",
                "La usabilidad es la capacidad del producto software para ser entendido, aprendido, usado y resultar atractivo para el usuario, cuando se usa bajo determinadas condiciones",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/velocidad.ogg")
        );
        exerciseList.add(velocidadComoda);

        // Creating therapists
        Therapist scaloni = Therapist.newTherapist(new NewTherapistDTO("Lionel", "Scaloni", 2130968583));
        scaloni.addExercise(exerciseList.stream().findFirst().get());
        therapistList.add(scaloni);
        userList.add(new User("Scalo", "123", scaloni));

        // Creating patients
        Patient dibu = Patient.newPatient(
                new NewPatientDTO("Emiliano", "Martinez", "tecomo@gmail.com", LocalDate.now().minusYears(32),
                        List.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY), LocalTime.of(15, 30), 2131165275),
                scaloni.getId());
        scaloni.addPatient(dibu);
        patientList.add(dibu);
        userList.add(new User("Dibu", "123", dibu));

        Patient messi = Patient.newPatient(
                new NewPatientDTO("Lionel Andrés", "Messi", "lio@gmail.com", LocalDate.now().minusYears(36),
                        List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), LocalTime.of(15, 30),2131165276),
                scaloni.getId());
        scaloni.addPatient(messi);
        patientList.add(messi);
        userList.add(new User("Messi", "123", messi));

        Patient depaul = Patient.newPatient(
                new NewPatientDTO("Rodrigo", "De Paul", "rodri@gmail.com", LocalDate.now().minusYears(28),
                        List.of(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY), LocalTime.of(15, 30), 2131165277),
                scaloni.getId());
        scaloni.addPatient(depaul);
        patientList.add(depaul);
        userList.add(new User("Rodri", "123", depaul));


        Patient paredes = Patient.newPatient(
                new NewPatientDTO("Leandro", "Paredes", "paredes@gmail.com", LocalDate.now().minusYears(29),
                        List.of(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY), LocalTime.of(15, 30), 2131165278),
                scaloni.getId());
        scaloni.addPatient(paredes);
        patientList.add(paredes);
        userList.add(new User("Paredes", "123", paredes));

        // Resolutions
        ExercisePractice exercisePracticeDepaul = ExercisePractice.newExercisePractice(new ExercisePracticeDTO("https://pf5302.s3.us-east-2.amazonaws.com/audios/toquesligeros.mp3"));
        ExercisePractice exercisePracticeMessi = ExercisePractice.newExercisePractice(new ExercisePracticeDTO("https://pf5302.s3.us-east-2.amazonaws.com/audios/iniciosuave.mp3"));
        exercisePracticeList.addAll(Arrays.asList(exercisePracticeMessi, exercisePracticeDepaul));

        // Assignments
        List<ExerciseAssignment> dibuAsignacion = Arrays.asList(new ExerciseAssignment(inicioSuave), new ExerciseAssignment(fonacionContinuada));
        dibu.addExercisesAssignment(dibuAsignacion);
        exerciseAssignmentList.addAll(dibuAsignacion);

        ExerciseAssignment asignacionInicioSuaveMessi = new ExerciseAssignment(inicioSuave);
        asignacionInicioSuaveMessi.addExercisePractice(exercisePracticeMessi);
        List<ExerciseAssignment> messiAsignacion = Arrays.asList(new ExerciseAssignment(fonacionContinuada), new ExerciseAssignment(velocidadComoda), asignacionInicioSuaveMessi);
        messi.addExercisesAssignment(messiAsignacion);
        exerciseAssignmentList.addAll(messiAsignacion);

        ExerciseAssignment asignacionToquesLigerosDepaul = new ExerciseAssignment(toquesLigeros);
        asignacionToquesLigerosDepaul.addExercisePractice(exercisePracticeDepaul);
        List<ExerciseAssignment> depaulAsignacion = Arrays.asList(asignacionToquesLigerosDepaul);
        depaul.addExercisesAssignment(depaulAsignacion);
        exerciseAssignmentList.addAll(depaulAsignacion);

        List<ExerciseAssignment> paredesAsignacion = Arrays.asList(new ExerciseAssignment(toquesLigeros),new ExerciseAssignment(inicioSuave), new ExerciseAssignment(fonacionContinuada), new ExerciseAssignment(velocidadComoda));
        paredes.addExercisesAssignment(paredesAsignacion);
        exerciseAssignmentList.addAll(paredesAsignacion);

        // Adding all to database
        exerciseRepo.saveAll(exerciseList);
        exercisePracticeRepo.saveAll(exercisePracticeList);
        exerciseAssignmentsRepo.saveAll(exerciseAssignmentList);
        patientRepo.saveAll(patientList);
        therapistRepo.saveAll(therapistList);
        userRepo.saveAll(userList);
    }

    public static void drop(PatientRepo patientRepo, TherapistRepo therapistRepo, ExerciseRepo exerciseRepo, UserRepo userRepo, ExerciseAssignmentsRepo exerciseAssignmentsRepo, ExercisePracticeRepo exercisePracticeRepo){
        patientRepo.deleteAll();
        exerciseRepo.deleteAll();
        therapistRepo.deleteAll();
        userRepo.deleteAll();
        exerciseAssignmentsRepo.deleteAll();
        exercisePracticeRepo.deleteAll();
    }
}
