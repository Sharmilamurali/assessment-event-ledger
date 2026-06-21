package com.assessment.event_gateway_service.service;

import com.assessment.event_gateway_service.client.AccountClient;
import com.assessment.event_gateway_service.entity.Event;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommunicationService {

    private final AccountClient accountClient;

    @CircuitBreaker(name = "accountService", fallbackMethod = "fallback")
    public String send(Event event) {

        return accountClient.sendTransaction(event.getAccountId(), event);

    }

    public String fallback(Event event, Exception ex) {

        return "Account service unavailable";
    }

}