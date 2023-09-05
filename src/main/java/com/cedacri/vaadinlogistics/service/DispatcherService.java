package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Dispatcher;
import com.cedacri.vaadinlogistics.repository.DispatcherRepository;
import com.cedacri.vaadinlogistics.service.genericService.GenericService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DispatcherService implements GenericService<Dispatcher> {

    private final DispatcherRepository repository;

    public DispatcherService(DispatcherRepository repository) {
        this.repository = repository;
    }

    public Dispatcher save(Dispatcher entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Dispatcher> getAll() {
        List<Dispatcher> carrierList = new ArrayList<>();
        Iterable<Dispatcher> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }

    public Dispatcher getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Dispatcher with id="+id));
    }

    public Dispatcher update(Dispatcher entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
