package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.repository.CollectiveRitualRepository;
import org.springframework.stereotype.Service;

@Service
public class FindCurrentRitualService {

    private final CollectiveRitualRepository collectiveRitualRepository;
    public FindCurrentRitualService(CollectiveRitualRepository collectiveRitualRepository) {
        this.collectiveRitualRepository = collectiveRitualRepository;
    }

    public CollectiveRitual execute() {
        return collectiveRitualRepository.findOpenRitual();
    }


}
