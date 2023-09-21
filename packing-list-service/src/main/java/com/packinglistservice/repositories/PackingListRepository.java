package com.packinglistservice.repositories;


import com.packinglistservice.entities.PackingElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PackingListRepository extends JpaRepository<PackingElement, UUID> {

    List<PackingElement> findAllByPostalItemId(UUID id);

}
