package com.vhre.corporateguardapi.modules.visitor.service;

import com.vhre.corporateguardapi.modules.visitor.dto.VisitorCreateRequest;
import com.vhre.corporateguardapi.modules.visitor.dto.VisitorResponse;
import com.vhre.corporateguardapi.modules.visitor.dto.VisitorUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VisitorService {
    VisitorResponse create(VisitorCreateRequest request);
    VisitorResponse update(UUID id, VisitorUpdateRequest request);
    VisitorResponse getById(UUID id);
    void delete(UUID id);
    Page<VisitorResponse> findAll(Pageable pageable);
    Page<VisitorResponse> search(String query, Pageable pageable);
}
