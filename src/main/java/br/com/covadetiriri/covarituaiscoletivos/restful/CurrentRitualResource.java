package br.com.covadetiriri.covarituaiscoletivos.restful;

import br.com.covadetiriri.covarituaiscoletivos.dto.CollectiveRitualResponse;
import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.service.FindCurrentRitualService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/ritual")
public class CurrentRitualResource {

    private final FindCurrentRitualService findCurrentRitualService;

    public CurrentRitualResource(FindCurrentRitualService findCurrentRitualService) {
        this.findCurrentRitualService = findCurrentRitualService;
    }

    @GetMapping
    public ResponseEntity<CollectiveRitualResponse> get() {
        CollectiveRitual collectiveRitual = findCurrentRitualService.execute();
        if (Objects.isNull(collectiveRitual)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectiveRitualResponse.fromEntity(collectiveRitual));
    }

}
