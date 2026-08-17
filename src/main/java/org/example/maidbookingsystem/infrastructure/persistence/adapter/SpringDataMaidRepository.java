package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import org.example.maidbookingsystem.infrastructure.persistence.entity.MaidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataMaidRepository extends JpaRepository<MaidEntity, UUID> {}

