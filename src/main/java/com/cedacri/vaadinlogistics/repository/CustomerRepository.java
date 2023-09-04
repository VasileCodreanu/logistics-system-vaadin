package com.cedacri.vaadinlogistics.repository;

import com.cedacri.vaadinlogistics.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("""
            SELECT c FROM Customer c
            WHERE LOWER(c.name) LIKE( CONCAT('%', :filterText, '%') )
            """)
    List<Customer> findByName(@Param("filterText")String filterText);
}
