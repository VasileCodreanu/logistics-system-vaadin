package com.cedacri.vaadinlogistics.service;



import com.cedacri.vaadinlogistics.model.Vehicle;
import com.cedacri.vaadinlogistics.repository.VehicleRepository;
import com.cedacri.vaadinlogistics.service.genericCrudService.GenericService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements GenericService<Vehicle> {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle save(Vehicle entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Vehicle> getAll() {
        List<Vehicle> carrierList = new ArrayList<>();
        Iterable<Vehicle> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }

    public Vehicle getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Vehicle with id="+ id));
    }

    public Vehicle update(Vehicle entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
