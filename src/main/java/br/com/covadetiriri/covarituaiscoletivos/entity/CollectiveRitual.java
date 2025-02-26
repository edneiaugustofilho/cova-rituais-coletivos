package br.com.covadetiriri.covarituaiscoletivos.entity;

import br.com.covadetiriri.covarituaiscoletivos.enums.CollectiveRitualStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CollectiveRitual")
@Table(schema = "public", name = "tb_colective_ritual")
public class CollectiveRitual extends AuditableEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "registration_deadline")
    private LocalDate registrationDeadline;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "current_registrations")
    private int currentRegistrations;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CollectiveRitualStatus status;

    @Column(name = "participation_fee")
    private BigDecimal participationFee;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
