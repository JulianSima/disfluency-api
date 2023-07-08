package com.disfluency.disfluencyapi.domain.sessions;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
public class SessionTurn {

    @Id
    private String id;
    private List<DayOfWeek> days;
    private LocalTime time;

    public SessionTurn(List<DayOfWeek> days, LocalTime time) {
        this.days = days;
        this.time = time;
    }
}
