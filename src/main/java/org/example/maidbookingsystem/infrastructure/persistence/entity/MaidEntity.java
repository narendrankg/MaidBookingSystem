package org.example.maidbookingsystem.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.maidbookingsystem.domain.Gender;
import org.example.maidbookingsystem.domain.ServiceType;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "maids")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MaidEntity {
    @Id
    private UUID id;
    private String name;
    private String locality;
    private BigDecimal hourlyPrice;
    private final BigDecimal rating = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection(targetClass = ServiceType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "maid_services", joinColumns = @JoinColumn(name = "maid_id"))
    @Column(name = "service")
    private Set<ServiceType> services = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "maid_skills", joinColumns = @JoinColumn(name = "maid_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();

    @OneToMany(mappedBy = "maid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingHourEntity> workingHours = new ArrayList<>();

    public MaidEntity(UUID id, String name, String locality, BigDecimal hourlyPrice,
                      Gender gender, Set<ServiceType> services, Set<String> skills) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.hourlyPrice = hourlyPrice;
        this.gender = gender;
        this.services.addAll(services);
        this.skills.addAll(skills);
    }

    public void addAvailabilityWindow(WorkingHourEntity window) {
        window.assignTo(this);
        workingHours.add(window);
    }
}

