package br.com.covadetiriri.covarituaiscoletivos.service;

import br.com.covadetiriri.covarituaiscoletivos.dto.RitualSubscriptionRequest;
import br.com.covadetiriri.covarituaiscoletivos.entity.CollectiveRitual;
import br.com.covadetiriri.covarituaiscoletivos.entity.Person;
import br.com.covadetiriri.covarituaiscoletivos.entity.RitualSubscription;
import br.com.covadetiriri.covarituaiscoletivos.enums.CollectiveRitualStatus;
import br.com.covadetiriri.covarituaiscoletivos.util.EmailAddressValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
public class RitualSubscribeService {

    private final PersonService personService;
    private final CollectiveRitualService collectiveRitualService;
    private final RitualSubscriptionService ritualSubscriptionService;

    public RitualSubscribeService(PersonService personService,
                                  CollectiveRitualService collectiveRitualService,
                                  RitualSubscriptionService ritualSubscriptionService) {
        this.personService = personService;
        this.collectiveRitualService = collectiveRitualService;
        this.ritualSubscriptionService = ritualSubscriptionService;
    }

    private void validate(RitualSubscriptionRequest request) {
        if (Objects.isNull(request.ritualID())) {
            throw new IllegalArgumentException("Identififcação do Ritual não foi fornecida.");
        }

        if (!collectiveRitualService.existsById(request.ritualID())) {
            throw new IllegalArgumentException("Identififcação do Ritual inválida.");
        }

        if (Objects.isNull(request.person())) {
            throw new IllegalArgumentException("Informações do participante do Ritual não foram fornecidas.");
        }

        if (!EmailAddressValidator.isValid(request.person().email())) {
            throw new IllegalArgumentException("E-mail do participante do Ritual é inválido.");
        }
    }

    private void validate(CollectiveRitual collectiveRitual) {
        if (!collectiveRitual.getStatus().equals(CollectiveRitualStatus.OPEN)) {
            throw new IllegalArgumentException("O Ritual não está aberto à inscrição.");
        }

        if (collectiveRitual.getRegistrationDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O prazo de inscrição do Ritual expirou.");
        }
    }

    public RitualSubscription execute(RitualSubscriptionRequest request) {
        validate(request);

        CollectiveRitual ritual = collectiveRitualService.get(request.ritualID());
        validate(ritual);

        RitualSubscription subscription = RitualSubscriptionRequest.asEntity(request);
        subscription.setCollectiveRitual(ritual);

        Person subscriber = subscription.getPerson();
        if (personService.existsByEmail(subscription.getPerson().getEmail())) {
            UUID foundPersonId = personService.getIdByEmail(subscriber.getEmail());
            subscriber.setId(foundPersonId);
            personService.update(subscriber);
        } else {
            subscriber = personService.create(subscriber);
        }
        subscription.setPerson(subscriber);

        if (ritualSubscriptionService.existsByPersonIdAndCollectiveRitualId(subscriber.getId(), ritual.getId())) {
            UUID subscriptionId = ritualSubscriptionService.getIdByPersonIdAndCollectiveRitualId(subscriber.getId(), ritual.getId());
            subscription.setId(subscriptionId);

            ritualSubscriptionService.update(subscription);
        } else {
            subscription = ritualSubscriptionService.create(subscription);
        }

        return subscription;
    }

}
