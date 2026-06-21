package com.assessment.event_gateway_service.client;

import com.assessment.event_gateway_service.entity.Event;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

@FeignClient(
name="account-service",
url="http://localhost:8081",
configuration=FeignConfig.class
)
public interface AccountClient {

@PostMapping("/accounts/{id}/transactions")
String sendTransaction(
@PathVariable String id,
@RequestBody Event event);

}