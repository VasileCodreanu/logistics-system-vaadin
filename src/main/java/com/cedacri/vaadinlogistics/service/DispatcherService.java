package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Customer;
import com.cedacri.vaadinlogistics.model.Dispatcher;
import com.cedacri.vaadinlogistics.repository.DispatcherRepository;
import com.cedacri.vaadinlogistics.service.genericCrudService.GenericService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatcherService implements GenericService<Dispatcher> {

  private final DispatcherRepository repository;
  private final SalaryCalculator calculator;

  public Dispatcher save(Dispatcher entity) {
    return repository.save(entity);
  }

  public List<Dispatcher> getAll() {
    List<Dispatcher> carrierList = new ArrayList<>();
    Iterable<Dispatcher> iterable = repository.findAll();
    iterable.forEach(carrierList::add);

    return carrierList;
  }

  public Dispatcher getById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Dispatcher with id=" + id));
  }

  public Dispatcher update(Dispatcher entity) {
    return repository.save(entity);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }


  public List<Dispatcher> findByName(String filterText) {
      if (null == filterText || filterText.isEmpty()) {
          return this.getAll();
      } else {
          return repository.findByName(filterText);
      }
  }

  public void calculateSalary() {
    List<Dispatcher> dispatchers = this.getAll();

    dispatchers
        .forEach(dispatcher -> {
          dispatcher.setSalary(calculator.calculateSalary(dispatcher));
          this.update(dispatcher);
        });
  }
}
