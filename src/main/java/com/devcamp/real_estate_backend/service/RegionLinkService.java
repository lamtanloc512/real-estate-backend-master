package com.devcamp.real_estate_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.RegionLink;
import com.devcamp.real_estate_backend.repository.RegionLinkRepository;

@Service
public class RegionLinkService extends GenericServiceImpl<RegionLink>{
    @Autowired
    private RegionLinkRepository regionLinkRepository;

    public RegionLinkService(RegionLinkRepository repository) {
        super(repository);
    }

    public RegionLink updateRegionLink(Integer id, RegionLink updatedRegionLink) {
        return regionLinkRepository.findById(id).map(existingRegionLink -> {
            // Update fields selectively from the body
            if (updatedRegionLink.getName() != null) {
                existingRegionLink.setName(updatedRegionLink.getName());
            }
            if (updatedRegionLink.getDescription() != null) {
                existingRegionLink.setDescription(updatedRegionLink.getDescription());
            }
            if (updatedRegionLink.getPhoto() != null) {
                existingRegionLink.setPhoto(updatedRegionLink.getPhoto());
            }
            if (updatedRegionLink.getAddress() != null) {
                existingRegionLink.setAddress(updatedRegionLink.getAddress());
            }
            return regionLinkRepository.save(existingRegionLink);
        }).orElseThrow(() -> new RuntimeException("Region Link not found with id " + id));
    }
}
