package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
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
public class Vehicle extends BaseEntity {

    private String vehicleNr;
    private String currentCityLocation;
//    @Enumerated(EnumType.STRING)
    private VehicleStatus currentStatus;

    @OneToMany(
        mappedBy = "vehicle",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private Set<Order> orderList = new HashSet<>();

    public enum VehicleStatus {
        FREE, BUSY, INACTIVE
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
        order.setVehicle(this);
    }
    public void removeOrder(Order order) {
        orderList.remove(order);
        order.setVehicle(null);//relly on orphan removal
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

        Vehicle vehicle = (Vehicle) o;

        if (!Objects.equals(vehicleNr, vehicle.vehicleNr)) {
            return false;
        }
        if (!Objects.equals(currentCityLocation, vehicle.currentCityLocation)) {
            return false;
        }
      return currentStatus == vehicle.currentStatus;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (vehicleNr != null ? vehicleNr.hashCode() : 0);
        result = 31 * result + (currentCityLocation != null ? currentCityLocation.hashCode() : 0);
        result = 31 * result + (currentStatus != null ? currentStatus.hashCode() : 0);
        return result;
    }
}

