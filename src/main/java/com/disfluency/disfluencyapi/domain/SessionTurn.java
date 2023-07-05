package com.disfluency.disfluencyapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SessionTurn {

    @Id
    private String id;
    private String days;
    private String time;

    public SessionTurn(String days, String time) {
        this.days = days;
        this.time = time;
    }
}
