package com.postalservice.controllers;

import com.packinglistservice.entities.PackingElement;
import com.postalservice.clients.PackingListClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/packing-list")
public class PackingListController {

    private final PackingListClient packingListClient;

    public PackingListController(PackingListClient packingListClient) {
        this.packingListClient = packingListClient;
    }

    @PostMapping("/register-packing-list")
    public ResponseEntity<Void> registerPackingList(@RequestBody List<PackingElement> packingList) {
        packingListClient.registerPackingList(packingList);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-packing-list/{id}")
    public List<PackingElement> findAllByPostalItemId(@PathVariable("id") UUID id) {
        return packingListClient.findAllByPostalItemId(id);
    }
}

