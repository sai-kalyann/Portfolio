package com.portfolio.contact_service.repository;

import com.portfolio.contact_service.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}



