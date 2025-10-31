package com.portfolio.project_service.service;

import com.portfolio.project_service.model.Project;
import com.portfolio.project_service.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> findAll() { return repository.findAll(); }
    public Project findById(Long id) { return repository.findById(id).orElseThrow(); }
    public Project create(Project p) { return repository.save(p); }
    public Project update(Long id, Project update) {
        Project p = findById(id);
        p.setTitle(update.getTitle());
        p.setDescription(update.getDescription());
        p.setTechStack(update.getTechStack());
        p.setImageURL(update.getImageURL());
        p.setProjectURL(update.getProjectURL());
        return repository.save(p);
    }
    public void delete(Long id) { repository.deleteById(id); }
}



