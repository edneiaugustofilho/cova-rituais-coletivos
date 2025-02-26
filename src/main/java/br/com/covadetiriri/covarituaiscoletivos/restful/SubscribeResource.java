package br.com.covadetiriri.covarituaiscoletivos.restful;

import br.com.covadetiriri.covarituaiscoletivos.dto.RitualSubscriptionRequest;
import br.com.covadetiriri.covarituaiscoletivos.entity.RitualSubscription;
import br.com.covadetiriri.covarituaiscoletivos.service.RitualSubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/subscribe")
public class SubscribeResource {

    private final RitualSubscribeService ritualSubscribeService;

    public SubscribeResource(RitualSubscribeService ritualSubscribeService) {
        this.ritualSubscribeService = ritualSubscribeService;
    }

    @PostMapping
    public ResponseEntity<UUID> subscribe(@RequestBody RitualSubscriptionRequest ritualSubscriptionRequest) {
        RitualSubscription subscription = ritualSubscribeService.execute(ritualSubscriptionRequest);

        return ResponseEntity.ok(subscription.getId());
    }


}
