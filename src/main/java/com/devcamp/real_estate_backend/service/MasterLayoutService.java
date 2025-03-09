package com.devcamp.real_estate_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.MasterLayout;
import com.devcamp.real_estate_backend.repository.MasterLayoutRepository;

@Service
public class MasterLayoutService extends GenericServiceImpl<MasterLayout>{
    @Autowired
    private MasterLayoutRepository masterLayoutRepository;

    public MasterLayoutService(MasterLayoutRepository repository) {
        super(repository);
    }

    public MasterLayout updateMasterLayout(Integer id, MasterLayout updatedMasterLayout) {
        return masterLayoutRepository.findById(id).map(existingMasterLayout -> {
            // Update fields selectively from the body
            if (updatedMasterLayout.getName() != null) {
                existingMasterLayout.setName(updatedMasterLayout.getName());
            }
            if (updatedMasterLayout.getDescription() != null) {
                existingMasterLayout.setDescription(updatedMasterLayout.getDescription());
            }
            if (updatedMasterLayout.getPhoto() != null) {
                existingMasterLayout.setPhoto(updatedMasterLayout.getPhoto());
            }
            if (updatedMasterLayout.getProject() != null) {
                existingMasterLayout.setProject(updatedMasterLayout.getProject());
            }
            if (updatedMasterLayout.getAcreage() != null) {
                existingMasterLayout.setAcreage(updatedMasterLayout.getAcreage());
            }
            if (updatedMasterLayout.getApartmentList() != null) {
                existingMasterLayout.setApartmentList(updatedMasterLayout.getApartmentList());
            }
            return masterLayoutRepository.save(existingMasterLayout);
        }).orElseThrow(() -> new RuntimeException("Utility not found with id " + id));
    }

    public List<MasterLayout> getMasterLayoutByProjectId(Integer projectId){
        return masterLayoutRepository.findByProjectId(projectId);
    }
}
