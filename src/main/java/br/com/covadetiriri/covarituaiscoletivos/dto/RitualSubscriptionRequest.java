package br.com.covadetiriri.covarituaiscoletivos.dto;

import br.com.covadetiriri.covarituaiscoletivos.entity.RitualSubscription;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public record RitualSubscriptionRequest(
        @NotNull
        PersonRequest person,
        @NotNull
        UUID ritualID,
        @NotNull
        boolean participatedBefore,
        @NotNull
        String howDidYouHearAboutRitual,
        String descriptionOfHowYouHeardAboutRitual,
        @NotNull
        boolean isAwareOfRitualInformation,
        @NotNull
        boolean quimbandaFamily,
        String whichQuimbandaFamily,
        @NotNull
        boolean hasDoneWorksInCipriano,
        @NotNull
        boolean hasDoneOracleInCipriano,
        @NotNull
        boolean doesMentoringWithKamuxinzela) {

        public static RitualSubscription asEntity(RitualSubscriptionRequest ritualSubscriptionRequest) {
            return RitualSubscription.builder().
                    person(PersonRequest.asEntity(ritualSubscriptionRequest.person())).
                    participatedBefore(ritualSubscriptionRequest.participatedBefore).
                    howDidYouHearAboutRitual(ritualSubscriptionRequest.howDidYouHearAboutRitual).
                    descriptionOfHowYouHeardAboutRitual(ritualSubscriptionRequest.descriptionOfHowYouHeardAboutRitual).
                    isAwareOfRitualInformation(ritualSubscriptionRequest.isAwareOfRitualInformation).
                    quimbandaFamily(ritualSubscriptionRequest.quimbandaFamily).
                    whichQuimbandaFamily(ritualSubscriptionRequest.whichQuimbandaFamily).
                    hasDoneWorksInCipriano(ritualSubscriptionRequest.hasDoneWorksInCipriano).
                    hasDoneOracleInCipriano(ritualSubscriptionRequest.hasDoneOracleInCipriano).
                    doesMentoringWithKamuxinzela(ritualSubscriptionRequest.doesMentoringWithKamuxinzela).
                    build();
        }
}