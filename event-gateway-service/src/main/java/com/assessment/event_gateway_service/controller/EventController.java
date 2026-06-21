package com.assessment.event_gateway_service.controller;

import com.assessment.event_gateway_service.entity.Event;
import com.assessment.event_gateway_service.repository.EventRepository;
import com.assessment.event_gateway_service.service.AccountCommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.assessment.event_gateway_service.exception.EventAlreadyExistsException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

private final EventRepository repository;
private final AccountCommunicationService accountService;

@PostMapping
public ResponseEntity<?> create(@Valid @RequestBody Event event){

if(repository.existsByEventId(event.getEventId())){

throw new EventAlreadyExistsException(
"Event already processed: "
+event.getEventId()
);

}

repository.save(event);

accountService.send(event);

return ResponseEntity.status(201).body(event);

}

@GetMapping("/{id}")
public Event get(@PathVariable Long id){

return repository.findById(id).orElseThrow();

}

@GetMapping
public List<Event> getByAccount(@RequestParam String account){

return repository.findByAccountIdOrderByEventTimestampAsc(account);

}

@GetMapping("/health")
public String health(){

return "UP";

}

}