package com.cedacri.vaadinlogistics.service;


import com.cedacri.vaadinlogistics.model.Customer;
import com.cedacri.vaadinlogistics.repository.CustomerRepository;
import com.cedacri.vaadinlogistics.service.genericCrudService.GenericService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements GenericService<Customer> {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
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
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Customer> findByName(String filterText) {
        if(null == filterText || filterText.isEmpty())
            return this.getAll();
        else
            return repository.findByName(filterText);
    }
}
