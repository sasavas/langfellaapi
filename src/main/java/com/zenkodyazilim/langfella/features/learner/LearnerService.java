package com.zenkodyazilim.langfella.features.learner;

import com.zenkodyazilim.langfella.common.exceptions.EntityDomainException;
import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.features.learner.dtos.LearnerCreateDTO;
import com.zenkodyazilim.langfella.features.learner.entities.Learner;
import com.zenkodyazilim.langfella.features.learner.entities.Translator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LearnerService {
    private final LearnerRepository learnerRepository;
    private final TranslatorRepository translatorRepository;

    @Transactional
    public Learner addNewLearner(LearnerCreateDTO learnerCreateDTO){
        var learner = new Learner();
        learner.setMainLanguage(learnerCreateDTO.mainLanguage());
        learner.setSubscription(learnerCreateDTO.subscription());
        learner.setSubscriptionActive(false);

        return learnerRepository.save(learner);
    }

    @Transactional
    public void addTranslator(long learnerId, long translatorId){
        var translator = translatorRepository.findById(translatorId)
                .orElseThrow(() -> new EntityNotFoundException(Translator.class.getSimpleName(), String.valueOf(translatorId)));

        var learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new EntityNotFoundException(Learner.class.getSimpleName(), String.valueOf(translatorId)));

        if(learner.getTranslators().contains(translator)){
            throw new EntityDomainException("The learner already uses this Translator");
        }

        learner.getTranslators().add(translator);
        learnerRepository.save(learner);
    }

    @Transactional
    public void removeTranslator(long learnerId, long translatorId){
        var translator = translatorRepository.findById(translatorId)
                .orElseThrow(() -> new EntityNotFoundException(Translator.class.getSimpleName(), String.valueOf(translatorId)));

        var learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new EntityNotFoundException(Learner.class.getSimpleName(), String.valueOf(translatorId)));

        learner.getTranslators().remove(translator);
        learnerRepository.save(learner);
    }

    public List<Translator> getAvailableTranslators(){
        return translatorRepository.findAll();
    }
}
