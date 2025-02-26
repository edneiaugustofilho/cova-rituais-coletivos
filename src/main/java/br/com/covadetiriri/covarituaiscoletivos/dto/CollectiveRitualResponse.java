package br.com.covadetiriri.covarituaiscoletivos.dto;

import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.enums.CollectiveRitualStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CollectiveRitualResponse(
        UUID id,
        String name, String description,
        LocalDate registrationDeadline, LocalDate startDate, LocalDate endDate,
        CollectiveRitualStatus status, BigDecimal participationFee,
        String instructions
) {

    public static CollectiveRitualResponse fromEntity(CollectiveRitual input) {
        return new CollectiveRitualResponse(input.getId(),
                input.getName(), input.getDescription(),
                input.getRegistrationDeadline(), input.getStartDate(), input.getEndDate(),
                input.getStatus(), input.getParticipationFee(),
                input.getInstructions());
    }

}
