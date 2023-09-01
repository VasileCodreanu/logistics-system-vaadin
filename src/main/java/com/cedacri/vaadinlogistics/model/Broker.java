package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Broker extends BaseEntity {

    @NotNull
    @NotBlank
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String phoneNr;

//    @Enumerated(EnumType.STRING)
    @NotNull
    private BrokerRating rating;

    @Embedded
    private Address address;

    @OneToMany(
        mappedBy = "broker",
        cascade = CascadeType.ALL,
        orphanRemoval = true)

    private Set<Order> orderList = new HashSet<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setBroker(this);
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
        order.setBroker(null);//relly on orphan removal
    }

    public enum BrokerRating {
        A, B, C, D
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Broker broker = (Broker) o;
        return Objects.equals(name, broker.name) && Objects.equals(email, broker.email) && Objects.equals(phoneNr, broker.phoneNr) && rating == broker.rating && Objects.equals(address, broker.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email, phoneNr, rating, address);
    }
}
