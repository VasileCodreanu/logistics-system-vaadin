package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Carrier;
import com.cedacri.vaadinlogistics.repository.CarrierRepository;
import com.cedacri.vaadinlogistics.service.genericService.GenericService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarrierService implements GenericService<Carrier> {
    private final CarrierRepository repository;

    public CarrierService(CarrierRepository repository) {
        this.repository = repository;
    }

    public Carrier save(Carrier entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Carrier> getAll() {
        List<Carrier> carrierList = new ArrayList<>();
        Iterable<Carrier> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }
    public Carrier getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Carrier with id= "+id));
    }

    public Carrier update(Carrier entity) {
        return repository.save(entity);
    }

    public void delete(Carrier entity) {
        repository.delete(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
