package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Order;
import com.cedacri.vaadinlogistics.repository.OrderRepository;
import com.cedacri.vaadinlogistics.service.genericService.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements GenericService<Order> {

    private final OrderRepository repository;

    public Order save(Order entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Order> getAll() {
        List<Order> carrierList = new ArrayList<>();
        Iterable<Order> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }

    public Order getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Order with id="+id));
    }

    //@Transactional
    public Order update(Order entity) {
        Order entityById = this.getById(entity.getId());
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
