package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.maid.MaidRepository;
import org.example.maidbookingsystem.application.maid.MaidSearchCriteria;
import org.example.maidbookingsystem.domain.Maid;

import org.example.maidbookingsystem.infrastructure.persistence.mapper.MaidMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaMaidRepositoryAdapter implements MaidRepository {
    private final SpringDataMaidRepository repository;
    private final MaidMapper mapper;

    @Override
    public Maid save(Maid maid) {
        return mapper.toDomain(repository.save(mapper.toEntity(maid)));
    }

    @Override
    public Optional<Maid> findById(UUID maidId) {
        return repository.findById(maidId).map(mapper::toDomain);
    }

    @Override
    public List<Maid> search(MaidSearchCriteria criteria) {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .filter(maid -> maid.locality().equals(criteria.locality()))
                .filter(maid -> criteria.requiredServices() == null ||
                        maid.services().containsAll(criteria.requiredServices()))
                .filter(maid -> criteria.requiredSkills() == null ||
                        maid.skills().containsAll(criteria.requiredSkills()))
                .filter(maid -> criteria.minimumRating() == null ||
                        maid.rating().compareTo(criteria.minimumRating()) >= 0)
                .filter(maid -> criteria.maximumHourlyPrice() == null ||
                        maid.hourlyPrice().compareTo(criteria.maximumHourlyPrice()) <= 0)
                .filter(maid -> criteria.genderPreference() == null ||
                        maid.gender() == criteria.genderPreference())
                .toList();
    }
}