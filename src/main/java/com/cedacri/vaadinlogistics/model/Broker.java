package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@NoArgsConstructor
@AllArgsConstructor
public class Broker extends BaseEntity {

    private String brokerName;
    private String phoneNr;
//    @Enumerated(EnumType.STRING)
    private BrokerRating brokerRating;

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Broker broker = (Broker) o;

        if (!Objects.equals(brokerName, broker.brokerName)) {
            return false;
        }
        if (!Objects.equals(phoneNr, broker.phoneNr)) {
            return false;
        }
        if (brokerRating != broker.brokerRating) {
            return false;
        }
      return Objects.equals(address, broker.address);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (brokerName != null ? brokerName.hashCode() : 0);
        result = 31 * result + (phoneNr != null ? phoneNr.hashCode() : 0);
        result = 31 * result + (brokerRating != null ? brokerRating.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
