package com.devcamp.real_estate_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.LegalDocument;
import com.devcamp.real_estate_backend.repository.LegalDocumentRepository;

@Service
public class LegalDocumentService extends GenericServiceImpl<LegalDocument>{
    @Autowired
    private LegalDocumentRepository legalDocumentRepository;

    public LegalDocumentService(LegalDocumentRepository repository) {
        super(repository);
    }

    public LegalDocument updateLegalDocument(Integer id, LegalDocument updatedLegalDocument) {
        return legalDocumentRepository.findById(id).map(existingLegalDocument -> {
            // Update fields selectively from the body
            if (updatedLegalDocument.getName() != null) {
                existingLegalDocument.setName(updatedLegalDocument.getName());
            }
            return legalDocumentRepository.save(existingLegalDocument);
        }).orElseThrow(() -> new RuntimeException("Legal Document not found with id " + id));
    }
}
