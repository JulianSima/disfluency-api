package com.disfluency.disfluencyapi.dto.analysis;

import com.disfluency.disfluencyapi.domain.disfluency.Disfluency;
import com.disfluency.disfluencyapi.domain.disfluency.DisfluencyResult;

public record DisfluencyResultDTO(
        Disfluency type,
        Integer count,
        Double percentageInTotalWords,
        Double percentageInDisfluencies
) {
    public static DisfluencyResultDTO from(DisfluencyResult disfluencyResult, Disfluency type){
        return new DisfluencyResultDTO(type, disfluencyResult.getCount(), disfluencyResult.getPercentageInTotalWords(), disfluencyResult.getPercentageInDisfluencies());
    }
}
