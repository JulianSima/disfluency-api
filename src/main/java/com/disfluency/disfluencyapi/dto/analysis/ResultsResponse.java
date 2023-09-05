package com.disfluency.disfluencyapi.dto.analysis;

import com.disfluency.disfluencyapi.domain.disfluency.DisfluencyResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultsResponse {

    @JsonProperty("I")
    private DisfluencyResult I;
    @JsonProperty("P")
    private DisfluencyResult P;
    @JsonProperty("RF")
    private DisfluencyResult RF;
    @JsonProperty("RP")
    private DisfluencyResult RP;
    @JsonProperty("Rs")
    private DisfluencyResult Rs;
    @JsonProperty("Rsi")
    private DisfluencyResult Rsi;
    @JsonProperty("V")
    private DisfluencyResult V;
    @JsonProperty("average disfluencies per phrase")
    private float averageDisfluenciesPerPhrase;
    @JsonProperty("fluency index")
    private float fluecyIndex;
    @JsonProperty("clean words")
    private Integer cleanWords;
    @JsonProperty("total words")
    private Integer totalWords;
    @JsonProperty("total phrases")
    private Integer totalPhrases;
    @JsonProperty("total disfluencies")
    private Integer totalDisfluencies;

}
