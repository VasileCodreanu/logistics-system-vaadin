package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dispatcher extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNr;
    private String email;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "dispatcher",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Order> orderList = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Dispatcher that = (Dispatcher) o;

        if (!Objects.equals(firstName, that.firstName)) {
            return false;
        }
        if (!Objects.equals(lastName, that.lastName)) {
            return false;
        }
        if (!Objects.equals(phoneNr, that.phoneNr)) {
            return false;
        }
        if (!Objects.equals(email, that.email)) {
            return false;
        }
      return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNr != null ? phoneNr.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
