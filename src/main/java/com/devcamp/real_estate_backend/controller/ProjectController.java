package com.devcamp.real_estate_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.Project;
import com.devcamp.real_estate_backend.service.ProjectService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/projects")
public class ProjectController extends GenericController<Project> {
    
    @Autowired
    private ProjectService projectService;

    public ProjectController(ProjectService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<Project> update(@PathVariable Integer id, @RequestBody Project project) {
        try {
            // Call the service to update the utility
            Project updatedProject = projectService.updateProject(id, project);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
