package br.com.covadetiriri.covarituaiscoletivos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RitualSubscription")
@Table(schema = "public", name = "tb_ritual_subscription")
public class RitualSubscription extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "collective_ritual_id")
    private CollectiveRitual collectiveRitual;

    @Column(name = "subscription_date")
    private LocalDate subscriptionDate;

    @Column(name = "participation_fee")
    private boolean participatedBefore;

    @Column(name = "how_did_you_hear_about_ritual")
    private String howDidYouHearAboutRitual;

    @Column(name = "description_of_how_you_heard_about_ritual")
    private String descriptionOfHowYouHeardAboutRitual;

    @Column(name = "is_aware_of_ritual_information")
    private boolean isAwareOfRitualInformation;

    @Column(name = "quimbanda_family")
    private boolean quimbandaFamily;

    @Column(name = "which_quimbanda_family")
    private String whichQuimbandaFamily;

    @Column(name = "has_done_works_in_cipriano")
    private boolean hasDoneWorksInCipriano;

    @Column(name = "has_done_oracle_in_cipriano")
    private boolean hasDoneOracleInCipriano;

    @Column(name = "does_mentoring_with_kamuxinzela")
    private boolean doesMentoringWithKamuxinzela;

}