package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import com.cedacri.vaadinlogistics.model.baseEntity.Employee;
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
public class Dispatcher extends Employee {

    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "dispatcher",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Order> orderList = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dispatcher that = (Dispatcher) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }
}
