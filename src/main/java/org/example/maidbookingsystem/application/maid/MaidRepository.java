package org.example.maidbookingsystem.application.maid;

import org.example.maidbookingsystem.domain.Maid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaidRepository {
    Maid save(Maid maid);
    Optional<Maid> findById(UUID maidId);
    List<Maid> search(MaidSearchCriteria criteria);
}