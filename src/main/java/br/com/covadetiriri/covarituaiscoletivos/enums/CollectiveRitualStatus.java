package br.com.covadetiriri.covarituaiscoletivos.enums;

import lombok.Getter;

@Getter
public enum CollectiveRitualStatus {
    
    PENDING("Pendente"),
    OPEN("Aberto"),
    CLOSED("Fechado"),
    FINISHED("Finalizado"),
    CANCELLED("Cancelado");
    
    private final String description;
    
    CollectiveRitualStatus(String description) {
        this.description = description;
    }
    
}
