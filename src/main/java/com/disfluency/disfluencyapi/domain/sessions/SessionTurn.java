package com.disfluency.disfluencyapi.domain.sessions;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
public class SessionTurn {

    private List<DayOfWeek> weeklyTurn;
    private LocalTime weeklyHour;

    public SessionTurn(List<DayOfWeek> weeklyTurn, LocalTime weeklyHour) {
        this.weeklyTurn = weeklyTurn;
        this.weeklyHour = weeklyHour;
    }
}
