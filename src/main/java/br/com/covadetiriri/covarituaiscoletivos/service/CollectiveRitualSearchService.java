package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.enums.CollectiveRitualStatus;
import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.repository.CollectiveRitualRepository;
import br.com.covadetiriri.covarituaiscoletivos.repository.RepositoryHelper;
import br.com.covadetiriri.covarituaiscoletivos.repository.SearchInput;
import br.com.covadetiriri.covarituaiscoletivos.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectiveRitualSearchService {

    private final CollectiveRitualRepository ritualRepository;

    public CollectiveRitualSearchService(CollectiveRitualRepository ritualRepository) {
        this.ritualRepository = ritualRepository;
    }

    @Getter
    public static class Input extends SearchInput {
        private final String name;
        private final CollectiveRitualStatus status;

        @Builder
        public Input(int pageNumber, int pageSize, String direction, String sortBy, String name, CollectiveRitualStatus status) {
            this.setPageNumber(pageNumber);
            this.setPageSize(pageSize);
            this.setDirection(direction);
            this.setSortBy(sortBy);
            this.name = name;
            this.status = status;
        }
    }

    public List<CollectiveRitual> searchRituals(Input input) {
        Pageable pageable = RepositoryHelper.buildPageable(input);

        return ritualRepository.findAll(
                (root, _, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();

                    if (StringUtils.isNotBlankOrNull(input.getName())) {
                        predicates.add(RepositoryHelper.likeCaseInsensitive(root, criteriaBuilder, "name", input.getName()));
                    }
                    if (input.getStatus() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("status"), input.getStatus()));
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
                }
        );
    }

}
