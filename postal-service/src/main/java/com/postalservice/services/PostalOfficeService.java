package com.postalservice.services;

import com.postalservice.entities.PostalOffice;

import java.util.Optional;

public interface PostalOfficeService {

    Optional<PostalOffice> findById(String officeIndex);

    PostalOffice createPostalOffice(PostalOffice postalOffice);
}
