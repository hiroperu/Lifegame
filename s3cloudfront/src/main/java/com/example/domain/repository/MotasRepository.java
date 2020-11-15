package com.example.domain.repository;

import java.util.Collection;
import com.example.domain.model.GetPDFLink;
import java.util.Optional;

public interface MotasRepository {
    Optional<GetPDFLink> findById(String id);
}
