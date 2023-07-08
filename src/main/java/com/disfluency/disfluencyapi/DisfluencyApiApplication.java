package com.disfluency.disfluencyapi;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.repository.ExerciseRepo;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import com.disfluency.disfluencyapi.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class DisfluencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisfluencyApiApplication.class, args);
	}

	/*@Bean
	CommandLineRunner initDatabase(PatientRepo patientRepo, TherapistRepo therapistRepo, UserRepo userRepo) {

		return args -> {
			Therapist messi = Therapist.newTherapist(
					new NewTherapistDTO("Lionel", "Messi", 2130968583)
			);
			User messiU = new User("Lio", "123", messi);

			Patient dibu = Patient.newPatient(
					new NewPatientDTO(
							"Emiliano", "Martinez", "tecomo@gmail.com", LocalDate.now().minusYears(5),
							List.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY), LocalTime.of(15, 30)),
					""
			);
			User dibuU = new User("Dibu", "123", dibu);

			messi.addPatient(dibu);
			patientRepo.save(dibu);
			therapistRepo.save(messi);
			userRepo.save(messiU);
			userRepo.save(dibuU);
		};
	}*/

	/*@Bean
	CommandLineRunner initDatabase(PatientRepo patientRepo, TherapistRepo therapistRepo, ExerciseRepo exerciseRepo, UserRepo userRepo) {
		return args -> {
			patientRepo.deleteAll();
			exerciseRepo.deleteAll();
			therapistRepo.deleteAll();
			userRepo.deleteAll();
		};
	}*/
}
