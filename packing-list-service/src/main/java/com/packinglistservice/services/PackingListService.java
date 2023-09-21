package com.packinglistservice.services;

import com.packinglistservice.entities.PackingElement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PackingListService {


    void createPackingList(List<PackingElement> packingList);

    List<PackingElement> findAllByPostalItemId(UUID id);
}


