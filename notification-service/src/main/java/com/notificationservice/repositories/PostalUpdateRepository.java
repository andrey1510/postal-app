package com.notificationservice.repositories;

import com.notificationservice.entities.PostalUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostalUpdateRepository extends JpaRepository<PostalUpdate, UUID> {

}
