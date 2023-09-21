package com.packinglistservice.services;

import com.packinglistservice.entities.PackingElement;
import com.packinglistservice.repositories.PackingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PackingListServiceImpl implements PackingListService {

    @Autowired
    private PackingListRepository packingListRepository;

    @Override
    @Transactional
    public void createPackingList(List<PackingElement> packingList) {
        packingListRepository.saveAll(packingList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PackingElement> findAllByPostalItemId(UUID id) {
        return packingListRepository.findAllByPostalItemId(id);
    }

}
