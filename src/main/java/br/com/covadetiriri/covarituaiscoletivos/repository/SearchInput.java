package br.com.covadetiriri.covarituaiscoletivos.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SearchInput {

    protected int pageNumber;
    protected int pageSize;

    protected String direction;
    protected String sortBy;

}
