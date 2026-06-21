package com.assessment.event_gateway_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventFlowIntegrationTest {

@Autowired
private TestRestTemplate restTemplate;

@Test
void gatewayToAccountFlowTest(){

String request = """
{
"eventId":"EVT100",
"accountId":"ACC100",
"type":"DEPOSIT",
"amount":100,
"eventTimestamp":"2026-06-21T10:00:00Z"
}
""";

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);

HttpEntity<String> entity =
new HttpEntity<>(request,headers);

ResponseEntity<String> response =
restTemplate.postForEntity(
"/events",
entity,
String.class
);

assertNotNull(response.getBody());

}

}
