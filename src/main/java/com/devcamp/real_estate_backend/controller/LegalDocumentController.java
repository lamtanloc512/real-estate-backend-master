package com.devcamp.real_estate_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.LegalDocument;
import com.devcamp.real_estate_backend.service.LegalDocumentService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/legal-documents")
public class LegalDocumentController extends GenericController<LegalDocument>{
    @Autowired
    private LegalDocumentService legalDocumentService;

    public LegalDocumentController(LegalDocumentService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<LegalDocument> update(@PathVariable Integer id, @RequestBody LegalDocument legalDocument) {
        try {
            // Call the service to update the utility
            LegalDocument updatedLegalDocument = legalDocumentService.updateLegalDocument(id, legalDocument);
            return ResponseEntity.ok(updatedLegalDocument);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
