package com.assessment.event_gateway_service.service;

import com.assessment.event_gateway_service.client.AccountClient;import com.assessment.event_gateway_service.entity.Event;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

class CircuitBreakerTest {

@Test
void accountServiceFailureShouldHandleError(){

AccountClient client =Mockito.mock(AccountClient.class);

Mockito.when(client.sendTransaction(Mockito.anyString(),Mockito.any(Event.class))).thenThrow(new RuntimeException());

Exception exception =assertThrows(RuntimeException.class,()->{
client.sendTransaction("ACC1",new Event());
});

assertNotNull(exception);

}

}