package com.disfluency.disfluencyapi.dto.therapists;

import com.disfluency.disfluencyapi.dto.patients.PatientSimpleDTO;

import java.util.List;

public record TherapistDTO(String id, String name, String lastName, Integer profilePictureUrl, List<PatientSimpleDTO> todayPatients) {
}
