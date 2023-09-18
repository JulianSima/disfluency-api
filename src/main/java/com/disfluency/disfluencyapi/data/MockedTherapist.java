package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.therapists.Therapist;

import java.util.List;

import static com.disfluency.disfluencyapi.data.MockedUser.PROFILE_PICTURES;

public class MockedTherapist {
    static final Therapist agus;
    static final Therapist ale;
    static final Therapist juli;
    static final Therapist pepe;

    static final List<Therapist> all;

    private MockedTherapist(){}

    static {
        agus = Therapist.builder()
                .name("Agustin")
                .lastName("Cragno")
                .profilePictureUrl( PROFILE_PICTURES[0])
                .patients(List.of(MockedPatient.dibu))
                .exercises(MockedExercise.all)
                .forms(MockedForm.all)
                .build();

        ale = Therapist.builder()
                .name("Alexander")
                .lastName("Martinez")
                .profilePictureUrl( PROFILE_PICTURES[1])
                .patients(List.of(MockedPatient.messi))
                .exercises(MockedExercise.all)
                .forms(MockedForm.all)
                .build();

        juli = Therapist.builder()
                .name("Julian")
                .lastName("Simaro")
                .profilePictureUrl( PROFILE_PICTURES[2])
                .patients(List.of(MockedPatient.depaul))
                .exercises(MockedExercise.all)
                .forms(MockedForm.all)
                .build();

        pepe = Therapist.builder()
                .name("Jose")
                .lastName("Bruzzoni")
                .profilePictureUrl( PROFILE_PICTURES[3])
                .patients(List.of(MockedPatient.paredes))
                .exercises(MockedExercise.all)
                .forms(MockedForm.all)
                .build();

        all = List.of(agus, ale, juli, pepe);
    }
}
