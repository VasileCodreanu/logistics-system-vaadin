package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT DISTINCT o FROM Order o
            LEFT JOIN FETCH o.carrier
            LEFT JOIN FETCH o.sender
            LEFT JOIN FETCH o.receiver
            LEFT JOIN FETCH o.dispatcher
            LEFT JOIN FETCH o.vehicle
            LEFT JOIN FETCH o.broker
            """)
    @Transactional(readOnly = true)
    List<Order> findAll();



}
