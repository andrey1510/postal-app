package com.notificationservice.controllers;

import com.notificationservice.dto.PostalSubscription;
import com.notificationservice.dto.PostalUpdateDTO;
import com.notificationservice.dto.PostalUpdateStatusAndTimestampDTO;
import com.notificationservice.mappers.PostalUpdateMapper;
import com.notificationservice.services.PostalUpdatesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/updates/")
@RequiredArgsConstructor
public class PostalUpdatesController {

    private static final String UPDATES_TOPIC_NAME = "${spring.kafka.updates-topic.name}";
    private static final String SUBSCRIPTION_TOPIC_NAME = "${spring.kafka.subscription-topic.name}";
    private static final String GROUP_ID = "${spring.kafka.consumer.group-id}";

    private final PostalUpdatesService postalUpdatesService;

    @PostMapping("subscribe")
    @Operation(description = "Подписаться на получение уведомлений об изменении статуса почтового отправления")
    public ResponseEntity<UUID> subscribeToUpdates(@RequestBody UUID id) {

        postalUpdatesService.sendMessage(SUBSCRIPTION_TOPIC_NAME, new PostalSubscription(id, true));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("unsubscribe")
    @Operation(description = "Отписаться от получения уведомлений об изменении статуса почтового отправления")
    public ResponseEntity<UUID> unsubscribeToUpdates(@RequestBody UUID id) {

        postalUpdatesService.sendMessage(SUBSCRIPTION_TOPIC_NAME, new PostalSubscription(id, false));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @KafkaListener(topics = UPDATES_TOPIC_NAME,
                   groupId = GROUP_ID,
                   properties = {"spring.json.value.default.type=com.notificationservice.dto.PostalUpdateDTO"})
    public void createUpdate(PostalUpdateDTO postalUpdateDTO) {

        postalUpdatesService.createPostalUpdate(PostalUpdateMapper.INSTANCE.dtoToEntity(postalUpdateDTO));
        
    }

    @GetMapping("last_update/{postal_item_id}")
    @Operation(description = "Найти последнее уведомление по идентификатору почтового отправления.")
    public PostalUpdateStatusAndTimestampDTO getLastUpdate(@PathVariable("postal_item_id") UUID postalItemId) {
        return PostalUpdateMapper.INSTANCE.entityToDTO(
                postalUpdatesService.getLastPostalUpdateByPostalItemId(postalItemId)
        );
    }

    @GetMapping("all_updates/{postal_item_id}")
    @Operation(description = "Найти все уведомления по идентификатору почтового отправления.")
    public List<PostalUpdateStatusAndTimestampDTO> getAllUpdates (@PathVariable("postal_item_id") UUID postalItemId) {
        return postalUpdatesService.getAllPostalUpdatesByPostalItemId(postalItemId).stream()
                .map(PostalUpdateMapper.INSTANCE::entityToDTO).toList();
    }


}
