package com.cedacri.vaadinlogistics.service;


import com.cedacri.vaadinlogistics.model.Customer;
import com.cedacri.vaadinlogistics.repository.ReceiverRepository;
import com.cedacri.vaadinlogistics.service.genericService.GenericService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements GenericService<Customer> {
    private final ReceiverRepository repository;

    public CustomerService(ReceiverRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Customer> getAll() {
        List<Customer> brokerList = new ArrayList<>();
        Iterable<Customer> iterable = repository.findAll();
        iterable.forEach(brokerList::add);

        return brokerList;
    }

    public Customer getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Customer with id="+ id));
    }

    public Customer update(Customer entity) {
        Customer entityById = this.getById(entity.getId());
//        entity.getReceiverOrders().addAll(entityById.getReceiverOrders());
//        entity.getSenderOrders().addAll(entityById.getSenderOrders());
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
