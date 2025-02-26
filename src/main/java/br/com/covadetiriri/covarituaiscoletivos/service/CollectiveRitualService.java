package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.dto.CollectiveRitualRequest;
import br.com.covadetiriri.covarituaiscoletivos.repository.CollectiveRitualRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CollectiveRitualService {

    private final CollectiveRitualRepository collectiveRitualRepository;

    public CollectiveRitualService(CollectiveRitualRepository collectiveRitualRepository) {
        this.collectiveRitualRepository = collectiveRitualRepository;
    }

    public boolean existsById(UUID id) {
        return collectiveRitualRepository.existsById(id);
    }

    public CollectiveRitual get(UUID id) {
        return collectiveRitualRepository.findById(id).orElse(null);
    }

    public CollectiveRitual create(CollectiveRitualRequest request) {
        CollectiveRitual collectiveRitual = CollectiveRitualRequest.asEntity(request);
        collectiveRitual.setCreatedBy("TODO: implementar o usuário logado");
        collectiveRitual.setCreatedAt(LocalDateTime.now());

        return collectiveRitualRepository.save(collectiveRitual);
    }

    public CollectiveRitual update(CollectiveRitualRequest request) {
        CollectiveRitual collectiveRitual = CollectiveRitualRequest.asEntity(request);
        collectiveRitual.setUpdatedBy("TODO: implementar o usuário logado");
        collectiveRitual.setUpdatedAt(LocalDateTime.now());

        return collectiveRitualRepository.save(collectiveRitual);
    }

    public void delete(UUID id) {

    }
}
