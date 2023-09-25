package com.compositeservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "postal-service")
public interface PostalClient {



}
