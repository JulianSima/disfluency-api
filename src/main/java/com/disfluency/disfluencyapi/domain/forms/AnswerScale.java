package com.disfluency.disfluencyapi.domain.forms;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class AnswerScale {

    @Id
    private String id;
    private String tag;
    private Integer value;


    public AnswerScale(String tag, Integer value) {
        this.tag = tag;
        this.value = value;
    }
}
