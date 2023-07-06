package com.disfluency.disfluencyapi.dto.users;

import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;

public record NewTherapistUserDTO(String account, String password, NewTherapistDTO user) {
}
