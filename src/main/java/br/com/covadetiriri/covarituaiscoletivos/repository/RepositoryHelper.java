package br.com.covadetiriri.covarituaiscoletivos.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class RepositoryHelper {

    public static Pageable buildPageable(SearchInput input) {
        if (input.getPageNumber() > 0 && input.getPageSize() > 0) {
            int page = input.getPageNumber() - 1;
            int size = input.getPageSize();

            if (input.getSortBy() != null) {
                Sort sort = (input.getDirection() != null)
                        ? Sort.by(input.getDirection(), input.getSortBy())
                        : Sort.by(input.getSortBy());
                return PageRequest.of(page, size, sort);
            }

            return PageRequest.of(page, size);
        }

        return Pageable.unpaged();
    }

    public static <T> Predicate likeCaseInsensitive(Root<T> root, CriteriaBuilder cb, String property, String value) {
        List<String> values = List.of(value.split(" "));

        List<Predicate> predicates = new ArrayList<>();
        for (String v : values) {
            predicates.add(cb.like(cb.lower(root.get(property)), "%" + v.toLowerCase() + "%"));
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }

}
