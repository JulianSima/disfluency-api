package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysisRepo extends MongoRepository<Analysis, String> {
}
