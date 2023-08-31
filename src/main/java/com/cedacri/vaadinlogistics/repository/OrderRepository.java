package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
