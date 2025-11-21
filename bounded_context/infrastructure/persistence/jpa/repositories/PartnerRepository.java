package com.prueba.bounded_context.infrastructure.persistence.jpa.repositories;

import com.prueba.bounded_context.domain.model.aggregates.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Partner p WHERE " +
           "p.representativeName.firstName = :firstName AND " +
           "p.representativeName.lastName = :lastName AND " +
           "p.companyName = :companyName AND " +
           "p.country = :country")
    boolean existsByRepresentativeNameAndCompanyNameAndCountry(
        @Param("firstName") String firstName,
        @Param("lastName") String lastName,
        @Param("companyName") String companyName,
        @Param("country") String country
    );
}
