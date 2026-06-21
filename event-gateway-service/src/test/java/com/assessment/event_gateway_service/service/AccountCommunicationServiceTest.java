package com.assessment.event_gateway_service.service;

import com.assessment.event_gateway_service.client.AccountClient;
import com.assessment.event_gateway_service.entity.Event;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountCommunicationServiceTest {

    @Test
    void sendTransaction_success() {

        AccountClient client = Mockito.mock(AccountClient.class);

        AccountCommunicationService service = new AccountCommunicationService(client);

        Event event = new Event();
        event.setEventId("EVT001");
        event.setAccountId("ACC001");

        assertDoesNotThrow(() -> service.send(event));

        Mockito.verify(client).sendTransaction("ACC001", event);
    }

    @Test
    void fallback_returnsDefaultMessage() {

        AccountClient client = Mockito.mock(AccountClient.class);

        AccountCommunicationService service = new AccountCommunicationService(client);

        String result = service.fallback(new Event(), new RuntimeException("service down"));

        assertEquals("Account service unavailable", result);
    }

    @Test
    void sendTransaction_whenAccountServiceFails() {

        AccountClient client = Mockito.mock(AccountClient.class);

        Mockito.doThrow(new RuntimeException("service down")).when(client).sendTransaction(Mockito.anyString(), Mockito.any(Event.class));

        AccountCommunicationService service = new AccountCommunicationService(client);

        Event event = new Event();
        event.setAccountId("ACC001");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.send(event));

        assertEquals("service down", exception.getMessage());
    }
}