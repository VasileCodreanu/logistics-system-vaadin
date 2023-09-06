package com.cedacri.vaadinlogistics.repository;


import com.cedacri.vaadinlogistics.model.Customer;
import com.cedacri.vaadinlogistics.model.Dispatcher;
import com.cedacri.vaadinlogistics.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DispatcherRepository extends CrudRepository<Dispatcher, Long> {

    @Query("""
            SELECT o FROM Order o left join o.broker b where b.id= :id
            """)
    @Transactional(readOnly = true)
    List<Order> getOrdersByDispatcherId(@Param("id") Long id);


    @Query("""
            SELECT d FROM Dispatcher d
            WHERE LOWER(d.firstName) LIKE( CONCAT('%', :filterText, '%') )
            """)
    @Transactional(readOnly = true)
    List<Dispatcher> findByName(@Param("filterText")String filterText);
}
