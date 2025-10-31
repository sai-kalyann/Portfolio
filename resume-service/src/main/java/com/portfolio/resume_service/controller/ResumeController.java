package com.portfolio.resume_service.controller;

import com.portfolio.resume_service.model.ResumeStats;
import com.portfolio.resume_service.repository.ResumeStatsRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeStatsRepository repository;

    public ResumeController(ResumeStatsRepository repository) {
        this.repository = repository;
    }

    private ResumeStats getOrCreate() {
        List<ResumeStats> all = repository.findAll();
        if (all.isEmpty()) {
            return repository.save(new ResumeStats());
        }
        return all.all.get(0);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download() {
        ResumeStats stats = getOrCreate();
        stats.setDownloadCount(stats.getDownloadCount() + 1);
        repository.save(stats);

        // Placeholder PDF bytes (simple text as application/pdf for now)
        byte[] content = "%PDF-1.4\n% Resume placeholder".getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(content);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        ResumeStats stats = getOrCreate();
        return ResponseEntity.ok(stats.getDownloadCount());
    }
}



