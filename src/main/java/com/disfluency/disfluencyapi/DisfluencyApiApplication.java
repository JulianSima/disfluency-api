package com.disfluency.disfluencyapi;

import com.disfluency.disfluencyapi.data.MockedData;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class DisfluencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisfluencyApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(PatientRepo patientRepo,
								   TherapistRepo therapistRepo,
								   UserRepo userRepo,
								   ExerciseRepo exerciseRepo,
								   ExerciseAssignmentsRepo exerciseAssignmentsRepo,
								   ExercisePracticeRepo exercisePracticeRepo) {


		return args -> {
//			MockedData.saveData(patientRepo, therapistRepo, exerciseRepo, userRepo, exerciseAssignmentsRepo, exercisePracticeRepo);
//			MockedData.drop(patientRepo, therapistRepo, exerciseRepo, userRepo, exerciseAssignmentsRepo, exercisePracticeRepo);
		};
	}

}
