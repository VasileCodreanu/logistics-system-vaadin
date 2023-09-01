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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrier extends BaseEntity {

    @NotNull
    @NotBlank
//    @Min(3)
    private String name;
    @Email
    @NotNull
    private String email;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "carrier",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Order> orderList = new HashSet<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setCarrier(this);
    }
    public void removeOrder(Order order) {
        orderList.remove(order);
        order.setCarrier(null);
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

        Carrier carrier = (Carrier) o;

        if (!Objects.equals(name, carrier.name)) {
            return false;
        }
      return Objects.equals(address, carrier.address);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
