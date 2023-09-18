package com.disfluency.disfluencyapi.dto.analysis;

import com.disfluency.disfluencyapi.domain.disfluency.Disfluency;
import com.disfluency.disfluencyapi.domain.disfluency.DisfluencyResult;

import java.util.List;

public record ResultsDTO(
        Float fluencyIndex,
        Integer totalWords,
        Integer cleanWords,
        Integer totalDisfluencies,
        Integer totalPhrases,
        Float averageDisfluenciesPerPhrase,
        List<DisfluencyResultDTO> disfluencyResults
){
    public static ResultsDTO from(ResultsResponse resultsResponse){
        return new ResultsDTO(
                resultsResponse.getFluecyIndex(),
                resultsResponse.getTotalWords(),
                resultsResponse.getCleanWords(),
                resultsResponse.getTotalDisfluencies(),
                resultsResponse.getTotalPhrases(),
                resultsResponse.getAverageDisfluenciesPerPhrase(),
                List.of(
                        DisfluencyResultDTO.from(resultsResponse.getI(), Disfluency.I),
                        DisfluencyResultDTO.from(resultsResponse.getRs(), Disfluency.Rs),
                        DisfluencyResultDTO.from(resultsResponse.getRsi(), Disfluency.Rsi),
                        DisfluencyResultDTO.from(resultsResponse.getRF(), Disfluency.RF),
                        DisfluencyResultDTO.from(resultsResponse.getRP(), Disfluency.RP),
                        DisfluencyResultDTO.from(resultsResponse.getV(), Disfluency.V),
                        DisfluencyResultDTO.from(resultsResponse.getP(), Disfluency.P)
                )
        );
    }
}
