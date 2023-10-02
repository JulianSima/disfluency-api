package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.service.users.PasswordService;

import java.util.List;

public class MockedUser {
    static final Integer[] PROFILE_PICTURES = {1, 2, 3, 4, 5, 6, 7};

    static final User agus;
    static final User ale;
    static final User juli;
    static final User pepe;
    static final User dibu;
    static final User messi;
    static final User depaul;
    static final User paredes;

    static final List<User> all;

    private MockedUser(){}

    static {
        PasswordService passwordService = new PasswordService();

        agus = new User("agus", passwordService.createPasswordHash("123"), MockedTherapist.agus);
        ale = new User("ale", passwordService.createPasswordHash("123"), MockedTherapist.ale);
        juli = new User("juli", passwordService.createPasswordHash("123"), MockedTherapist.juli);
        pepe = new User("pepe", passwordService.createPasswordHash("123"), MockedTherapist.pepe);
        dibu = new User("Dibu", passwordService.createPasswordHash("123"), MockedPatient.dibu);
        messi = new User("Messi", passwordService.createPasswordHash("123"), MockedPatient.messi);
        depaul = new User("Rodri", passwordService.createPasswordHash("123"), MockedPatient.depaul);
        paredes = new User("Paredes", passwordService.createPasswordHash("123"), MockedPatient.paredes);

        all = List.of(agus, ale, juli, pepe, dibu, messi, depaul, paredes);
    }
}
