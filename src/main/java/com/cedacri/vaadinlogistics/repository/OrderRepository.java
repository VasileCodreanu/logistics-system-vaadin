package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT o FROM Order o
            JOIN FETCH o.broker broker
            JOIN FETCH o.carrier carrier
            JOIN FETCH o.sender sender
            JOIN FETCH o.receiver receiver
            JOIN FETCH o.dispatcher dispatcher
            JOIN FETCH o.vehicle
            """)
    List<Order> findAll();



}
