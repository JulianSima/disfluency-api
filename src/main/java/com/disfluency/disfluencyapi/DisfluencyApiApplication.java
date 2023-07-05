package com.disfluency.disfluencyapi;

import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DisfluencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisfluencyApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(PatientRepo patientRepo, TherapistRepo therapistRepo) {

		return args -> {
			/*Therapist messi = Therapist.newTherapist(new NewTherapistDTO("Lionel", "Messi"));
			Patient dibu = Patient.newPatient(new NewPatientDTO("Emiliano", "Martinez",
					"tecomo@gmail.com", LocalDate.now(), "Lunes y Miercoles","18:00"), "");
			messi.addPatient(dibu);
			patientRepo.save(dibu);
			therapistRepo.save(messi);*/
		};
	}

}
