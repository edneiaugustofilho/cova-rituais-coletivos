package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.entity.RitualSubscription;
import br.com.covadetiriri.covarituaiscoletivos.repository.RitualSubscriptionRepository;
import br.com.covadetiriri.covarituaiscoletivos.util.ContextUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RitualSubscriptionService {

    private final RitualSubscriptionRepository ritualSubscriptionRepository;

    public RitualSubscriptionService(RitualSubscriptionRepository ritualSubscriptionRepository) {
        this.ritualSubscriptionRepository = ritualSubscriptionRepository;
    }

    public boolean existsByPersonIdAndCollectiveRitualId(UUID personId, UUID collectiveRitualId) {
        return ritualSubscriptionRepository.existsByPersonIdAndCollectiveRitualId(personId, collectiveRitualId);
    }

    public RitualSubscription create(RitualSubscription ritualSubscription) {
        ritualSubscription.setCreatedAt(LocalDateTime.now());
        ritualSubscription.setCreatedBy(ContextUtil.getCurrentUsername());

        return ritualSubscriptionRepository.save(ritualSubscription);
    }

    public void update(RitualSubscription ritualSubscription) {
        ritualSubscription.setUpdatedAt(LocalDateTime.now());
        ritualSubscription.setUpdatedBy(ContextUtil.getCurrentUsername());

        ritualSubscriptionRepository.save(ritualSubscription);
    }

    public UUID getIdByPersonIdAndCollectiveRitualId(UUID id, UUID id1) {
        return ritualSubscriptionRepository.getIdByPersonIdAndCollectiveRitualId(id, id1);
    }
}
