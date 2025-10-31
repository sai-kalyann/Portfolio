package com.portfolio.resume_service.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "resume_stats")
public class ResumeStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer downloadCount = 0;
    private Instant timestamp = Instant.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}



