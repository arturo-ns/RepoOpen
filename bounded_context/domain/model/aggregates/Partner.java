package com.prueba.bounded_context.domain.model.aggregates;

import com.prueba.bounded_context.domain.model.valueobjects.LunarisPartnerId;
import com.prueba.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.prueba.shared.domain.model.valueobjects.EmailAddress;
import com.prueba.shared.domain.model.valueobjects.PersonName;
import com.prueba.shared.domain.model.valueobjects.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Partner extends AuditableAbstractAggregateRoot<Partner> {
    
    @Getter
    @Embedded
    @Column(name = "partner_id")
    private LunarisPartnerId partnerId;

    @Embedded
    private PersonName representativeName;

    @Embedded
    private PhoneNumber contactPhone;

    @Embedded
    private EmailAddress contactEmail;

    @Getter
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Getter
    @Column(name = "city", nullable = false)
    private String city;

    @Getter
    @Column(name = "state_or_province")
    private String stateOrProvince;

    @Getter
    @Column(name = "country", nullable = false)
    private String country;

    public Partner() {
        super();
        this.partnerId = new LunarisPartnerId();
    }

    public Partner(PersonName representativeName, PhoneNumber contactPhone, 
                   EmailAddress contactEmail, String companyName, 
                   String city, String stateOrProvince, String country) {
        this();
        this.representativeName = representativeName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.companyName = companyName != null ? companyName.trim() : null;
        this.city = city != null ? city.trim() : null;
        this.stateOrProvince = stateOrProvince != null ? stateOrProvince.trim() : null;
        this.country = country != null ? country.trim() : null;
    }

    public String getPartnerIdValue() {
        return this.partnerId != null ? this.partnerId.getValue() : null;
    }

    public String getRepresentativeFirstName() {
        return this.representativeName != null ? this.representativeName.getFirstName() : null;
    }

    public String getRepresentativeLastName() {
        return this.representativeName != null ? this.representativeName.getLastName() : null;
    }

    public String getContactPhoneValue() {
        return this.contactPhone != null ? this.contactPhone.getValue() : null;
    }

    public String getContactEmailValue() {
        return this.contactEmail != null ? this.contactEmail.getValue() : null;
    }
}
