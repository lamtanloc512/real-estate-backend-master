package com.devcamp.real_estate_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.Project;
import com.devcamp.real_estate_backend.repository.ProjectRepository;

@Service
public class ProjectService extends GenericServiceImpl<Project>{
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository repository) {
        super(repository);
    }

    public Project updateProject(Integer id, Project updatedProject) {
        return projectRepository.findById(id).map(existingProject -> {
            // Update fields selectively from the body
            if (updatedProject.getName() != null) {
                existingProject.setName(updatedProject.getName());
            }
            if (updatedProject.getDescription() != null) {
                existingProject.setDescription(updatedProject.getDescription());
            }
            if (updatedProject.getAcreage() != null) {
                existingProject.setAcreage(updatedProject.getAcreage());
            }
            if (updatedProject.getAddress() != null) {
                existingProject.setAddress(updatedProject.getAddress());
            }
            if (updatedProject.getDesignUnit() != null) {
                existingProject.setDesignUnit(updatedProject.getDesignUnit());
            }
            if (updatedProject.getInvestor() != null) {
                existingProject.setInvestor(updatedProject.getInvestor());
            }
            if (updatedProject.getDistrict() != null) {
                existingProject.setDistrict(updatedProject.getDistrict());
            }
            if (updatedProject.getConstructionContractor() != null) {
                existingProject.setConstructionContractor(updatedProject.getConstructionContractor());
            }
            if (updatedProject.getLatitude() != null) {
                existingProject.setLatitude(updatedProject.getLatitude());
            }
            if (updatedProject.getLongitude() != null) {
                existingProject.setLongitude(updatedProject.getLongitude());
            }
            if (updatedProject.getNumApartment() != null) {
                existingProject.setNumApartment(updatedProject.getNumApartment());
            }
            if (updatedProject.getNumBlock() != null) {
                existingProject.setNumBlock(updatedProject.getNumBlock());
            }
            if (updatedProject.getNumFloors() != null) {
                existingProject.setNumFloors(updatedProject.getNumFloors());
            }
            if (updatedProject.getProvince() != null) {
                existingProject.setProvince(updatedProject.getProvince());
            }
            if (updatedProject.getRegionLink() != null) {
                existingProject.setRegionLink(updatedProject.getRegionLink());
            }
            if (updatedProject.getUtilities() != null) {
                existingProject.setUtilities(updatedProject.getUtilities());
            }
            if (updatedProject.getWard() != null) {
                existingProject.setWard(updatedProject.getWard());
            }
            if (updatedProject.getPhoto() != null) {
                existingProject.setPhoto(updatedProject.getPhoto());
            }
            return projectRepository.save(existingProject);
        }).orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }

}
