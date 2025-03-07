package br.com.covadetiriri.covarituaiscoletivos.repository;

import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CollectiveRitualRepository extends JpaRepository<CollectiveRitual, UUID>, JpaSpecificationExecutor<CollectiveRitual> {

    @Query("select c " +
            "from CollectiveRitual c " +
            "where c.status = 'OPEN' " +
            "order by c.createdAt asc " +
            "limit 1")
    CollectiveRitual findOpenRitual();
}
