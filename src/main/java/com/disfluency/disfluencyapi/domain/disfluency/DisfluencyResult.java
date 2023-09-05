package com.disfluency.disfluencyapi.domain.disfluency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DisfluencyResult {
    private Integer count;
    @JsonProperty("percentage in total words")
    private Double percentageInTotalWords;
    @JsonProperty("percetage in disfluencies")
    private Double percentageInDisfluencies;
}
