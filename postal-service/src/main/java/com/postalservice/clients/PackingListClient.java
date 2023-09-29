package com.postalservice.clients;

import com.packinglistservice.entities.PackingElement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "packing-list-service")
public interface PackingListClient {

    @PostMapping("/api/packing_list/register")
    void registerPackingList(List<PackingElement> packingList);

    @GetMapping("/api/packing_list/find/{id}")
    List<PackingElement> findAllByPostalItemId(@PathVariable("id") UUID id);

}

