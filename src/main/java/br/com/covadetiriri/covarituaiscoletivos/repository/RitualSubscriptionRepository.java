package br.com.covadetiriri.covarituaiscoletivos.repository;

import br.com.covadetiriri.covarituaiscoletivos.entity.RitualSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RitualSubscriptionRepository extends JpaRepository<RitualSubscription, UUID> {
    boolean existsByPersonIdAndCollectiveRitualId(UUID personId, UUID collectiveRitualId);

    @Query(value = "select r.id " +
            "from tb_ritual_subscription r " +
            "where r.person_id = :personId " +
            "and r.collective_ritual_id = :ritualId", nativeQuery = true)
    UUID getIdByPersonIdAndCollectiveRitualId(UUID personId, UUID ritualId);
}
