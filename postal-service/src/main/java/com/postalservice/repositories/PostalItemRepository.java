package com.postalservice.repositories;

import com.postalservice.entities.PostalItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostalItemRepository extends JpaRepository<PostalItem, UUID> {

}
