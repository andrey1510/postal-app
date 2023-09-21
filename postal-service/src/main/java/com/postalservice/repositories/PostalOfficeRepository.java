package com.postalservice.repositories;

import com.postalservice.entities.PostalOffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, String> {

}
