package com.postalservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "packing-list-service")
public interface PackingListClient {

}
