package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
