package com.packinglistservice.controllers;

import com.packinglistservice.entities.PackingElement;
import com.packinglistservice.services.PackingListService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/packing_list/")
@RequiredArgsConstructor
public class PackingListController {

    private final PackingListService packingListService;

    @PostMapping("register")
    @Operation(description = "Внести в базу опись.")
    public ResponseEntity<List<PackingElement>> registerPackingList(@RequestBody List<PackingElement> packingList) {

        packingListService.createPackingList(packingList);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    @Operation(description = "Найти опись по идентификатору почтового отправления.")
    public List<PackingElement> findAllByPostalItemId(@PathVariable("id") UUID id) {
        return packingListService.findAllByPostalItemId(id);
    }

}
