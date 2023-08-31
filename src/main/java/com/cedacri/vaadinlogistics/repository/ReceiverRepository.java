package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ReceiverRepository extends CrudRepository<Customer, Long> {
}
