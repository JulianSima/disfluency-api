package com.disfluency.disfluencyapi;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapist.Therapist;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.repository.ExerciseRepo;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;

@SpringBootApplication
@EnableMongoRepositories
public class DisfluencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisfluencyApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(PatientRepo patientRepo, TherapistRepo therapistRepo) {

		return args -> {
			Therapist messi = Therapist.newTherapist(new NewTherapistDTO("Lionel", "Messi", "1"));
			Patient dibu = Patient.newPatient(new NewPatientDTO("Emiliano", "Martinez",
					"tecomo@gmail.com", LocalDate.now(), "Lunes y Miercoles","18:00"), "");
			messi.addPatient(dibu);
			patientRepo.save(dibu);
			therapistRepo.save(messi);
		};
	}

	/*@Bean
	CommandLineRunner initDatabase(PatientRepo patientRepo, TherapistRepo therapistRepo, ExerciseRepo exerciseRepo) {

		return args -> {
			patientRepo.deleteAll();
			therapistRepo.deleteAll();
			exerciseRepo.deleteAll();
		};
	}*/

}
