package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Broker;
import com.cedacri.vaadinlogistics.repository.BrokerRepository;
import com.cedacri.vaadinlogistics.service.genericService.GenericService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

@Service
public class BrokerService implements CrudListener<Broker>, GenericService<Broker> {

    private final BrokerRepository repository;


    public BrokerService(BrokerRepository repository) {
        this.repository = repository;
    }

    public Broker save(Broker entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Broker> getAll() {
        List<Broker> brokerList = new ArrayList<>();
        Iterable<Broker> iterable = repository.findAll();
        iterable.forEach(brokerList::add);

        return brokerList;
    }
    public Broker getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Broker with id="+id));
    }

    public Broker update(Broker entity) {
        Broker entityById = this.getById(entity.getId());
        entity.getOrderList().addAll(entityById.getOrderList());
        return repository.save(entity);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Broker broker) {
        this.deleteById(broker.getId());
    }
    @Override
    public Collection<Broker> findAll() {
        return this.getAll();
    }

    @Override
    public Broker add(Broker broker) {
        return this.save(broker);
    }

}
