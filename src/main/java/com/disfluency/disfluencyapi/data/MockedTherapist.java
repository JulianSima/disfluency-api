package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.therapists.Therapist;

import java.util.ArrayList;
import java.util.List;

import static com.disfluency.disfluencyapi.data.MockedUser.PROFILE_PICTURES;

public class MockedTherapist {
    static final Therapist scaloni;

    static final List<Therapist> all;

    private MockedTherapist(){}

    static {
        scaloni = Therapist.builder()
                .name("Lionel")
                .lastName("Scaloni")
                .profilePictureUrl( PROFILE_PICTURES[0])
                .patients(MockedPatient.all)
                .exercises(MockedExercise.all)
                .forms(new ArrayList<>())
                .build();

        all = List.of(scaloni);
    }
}
