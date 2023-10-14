package com.disfluency.disfluencyapi;

import com.disfluency.disfluencyapi.data.MockedData;
import com.disfluency.disfluencyapi.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
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
								   ExercisePracticeRepo exercisePracticeRepo,
								   FormRepo formRepo,
								   FormQuestionRepo formQuestionRepo,
								   FormAssignmentRepo formAssignmentRepo,
								   FormQuestionResponseRepo formQuestionResponseRepo,
								   FormCompletionEntryRepo formCompletionEntryRepo,
								   AnalysisRepo analysisRepo
	) {


		return args -> {
// 			MockedData.drop(patientRepo, therapistRepo, exerciseRepo, userRepo, exerciseAssignmentsRepo, exercisePracticeRepo, formRepo, formQuestionRepo, formAssignmentRepo, formQuestionResponseRepo, formCompletionEntryRepo, analysisRepo);
//			MockedData.saveData(patientRepo, therapistRepo, exerciseRepo, userRepo, exerciseAssignmentsRepo, exercisePracticeRepo, formRepo, formQuestionRepo, formAssignmentRepo, formQuestionResponseRepo, formCompletionEntryRepo);
		};
	}

}
