package br.com.covadetiriri.covarituaiscoletivos.dto;

import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.enums.CollectiveRitualStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CollectiveRitualRequest(
        String name,
        String description,
        LocalDate registrationDeadline,
        LocalDate startDate,
        LocalDate endDate,
        CollectiveRitualStatus status,
        BigDecimal participationFee,
        String instructions
) {

    public static CollectiveRitual asEntity(CollectiveRitualRequest input) {
        CollectiveRitual collectiveRitual = new CollectiveRitual();
        collectiveRitual.setName(input.name());
        collectiveRitual.setDescription(input.description());
        collectiveRitual.setRegistrationDeadline(input.registrationDeadline());
        collectiveRitual.setStartDate(input.startDate());
        collectiveRitual.setEndDate(input.endDate());
        collectiveRitual.setStatus(input.status());
        collectiveRitual.setParticipationFee(input.participationFee());
        collectiveRitual.setInstructions(input.instructions());
        return collectiveRitual;
    }
}
