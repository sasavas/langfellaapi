package com.zenkodyazilim.langfella.features.learner.api;

import com.zenkodyazilim.langfella.features.learner.LearnerService;
import com.zenkodyazilim.langfella.features.learner.dtos.LearnerCreateDTO;
import com.zenkodyazilim.langfella.features.learner.repositories.LearnerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LearnerServiceApi {
    private final LearnerRepository learnerRepository;
    private final LearnerService learnerService;

    public List<Translator> getLearnerTranslators(long learnerId) {
        var learner = learnerRepository.findById(learnerId).orElseThrow();
        return learner.getTranslators().stream().map(t -> new Translator(t.getName())).toList();
    }

    /**
     * expect userId, relate learner to user
     */
    @Transactional
    public void createNewLearner(LearnerCreateDTO dto){
        learnerService.createNewLearner(dto);
    }
}
