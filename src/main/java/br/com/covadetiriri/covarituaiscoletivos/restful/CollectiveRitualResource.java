package br.com.covadetiriri.covarituaiscoletivos.restful;

import br.com.covadetiriri.covarituaiscoletivos.enums.CollectiveRitualStatus;
import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.dto.CollectiveRitualRequest;
import br.com.covadetiriri.covarituaiscoletivos.dto.CollectiveRitualResponse;
import br.com.covadetiriri.covarituaiscoletivos.service.CollectiveRitualSearchService;
import br.com.covadetiriri.covarituaiscoletivos.service.CollectiveRitualService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/collective-rituals")
public class CollectiveRitualResource {

    private final CollectiveRitualService collectiveRitualService;
    private final CollectiveRitualSearchService collectiveRitualSearchService;

    public CollectiveRitualResource(CollectiveRitualService collectiveRitualService,
                                    CollectiveRitualSearchService collectiveRitualSearchService) {
        this.collectiveRitualService = collectiveRitualService;
        this.collectiveRitualSearchService = collectiveRitualSearchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectiveRitualResponse> get(@PathVariable UUID id) {
        CollectiveRitual collectiveRitual = this.collectiveRitualService.get(id);
        if (Objects.isNull(collectiveRitual)) {
            return ResponseEntity.noContent().build();
        }
        CollectiveRitualResponse collectiveRitualResponse = CollectiveRitualResponse.fromEntity(collectiveRitual);
        return ResponseEntity.ok(collectiveRitualResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CollectiveRitualResponse>> search(@RequestParam("query") String query,
                                                                 @RequestParam("status") CollectiveRitualStatus status,
                                                                 @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                                                                 @RequestParam("sortBy") String sortBy, @RequestParam("direction") String direction) {

        CollectiveRitualSearchService.Input input = CollectiveRitualSearchService.Input.builder().
                name(query).status(status).pageNumber(pageNumber).
                pageSize(pageSize).sortBy(sortBy).direction(direction).
                build();

        List<CollectiveRitualResponse> collectiveRituals = this.collectiveRitualSearchService.searchRituals(input).
                stream().map(CollectiveRitualResponse::fromEntity).toList();

        return ResponseEntity.ok(collectiveRituals);
    }

    @PostMapping
    public ResponseEntity<CollectiveRitualResponse> create(@RequestBody CollectiveRitualRequest input) {
        CollectiveRitualResponse collectiveRitualResponse =
                CollectiveRitualResponse.fromEntity(this.collectiveRitualService.create(input));

        return ResponseEntity.ok(collectiveRitualResponse);
    }

    @PutMapping
    public ResponseEntity<CollectiveRitualResponse> update(@RequestBody CollectiveRitualRequest input) {
        CollectiveRitualResponse collectiveRitualResponse =
                CollectiveRitualResponse.fromEntity(this.collectiveRitualService.update(input));

        return ResponseEntity.ok(collectiveRitualResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CollectiveRitualResponse> delete(@PathVariable UUID id) {
        this.collectiveRitualService.delete(id);

        return ResponseEntity.ok().build();
    }

}
