package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Broker;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BrokerRepository extends CrudRepository<Broker, Long> {


  @Query("""
            SELECT DISTINCT b FROM Broker b LEFT JOIN FETCH b.orderList
            """)
  List<Broker> findAll();
}
