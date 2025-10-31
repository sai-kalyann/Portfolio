package com.portfolio.contact_service.controller;

import com.portfolio.contact_service.model.ContactMessage;
import com.portfolio.contact_service.repository.ContactMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactMessageRepository repository;

    public ContactController(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<ContactMessage> submit(@RequestBody ContactMessage msg) {
        return ResponseEntity.ok(repository.save(msg));
    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> list() {
        return ResponseEntity.ok(repository.findAll());
    }
}



