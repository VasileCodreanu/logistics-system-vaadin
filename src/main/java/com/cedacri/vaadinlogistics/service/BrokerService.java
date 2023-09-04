package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Broker;
import com.cedacri.vaadinlogistics.repository.BrokerRepository;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerService {

    private final BrokerRepository repository;

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
        return repository.save(entity);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
