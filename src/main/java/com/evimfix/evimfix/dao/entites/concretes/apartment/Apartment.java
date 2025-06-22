package com.evimfix.evimfix.dao.entites.concretes.apartment;

import com.evimfix.evimfix.dao.entites.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Apartment extends BaseEntity {

    private String title;
    private BigDecimal price;
    private String about;
    private Integer roomCount;
    private BigDecimal area;
    private Integer floor;
    private Integer floorCount;
    private Boolean isRepair;
    private String repairName;
    private String address;
    private Boolean isRental;

    @ManyToOne
    private ApartmentType apartmentType;

    @ManyToOne
    private ApartmentOwner apartmentOwner;

    @ManyToOne
    private BuildingType buildingType;

    @ManyToOne
    private City city;

    @ManyToOne
    private District district;

    @ManyToOne
    private OwnershipCertificate ownershipCertificate;

    @ManyToOne
    private Metro metro;

    @ManyToMany
    private Set<Photo> photo;
}
