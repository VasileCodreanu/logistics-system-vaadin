package com.cedacri.vaadinlogistics.service.managerService;


import com.cedacri.vaadinlogistics.model.Order;
import com.cedacri.vaadinlogistics.repository.OrderRepository;
import com.cedacri.vaadinlogistics.service.genericService.GenericService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderManagerService implements GenericService<Order> {

  private final OrderRepository repository;


  public Order save(Order entity) {
    return repository.save(entity);
  }

  @Transactional(readOnly = true)
  public List<Order> getAll() {
    if (repository.findAll().isEmpty()) {
      throw new RuntimeException("No Order To be Returned!");
    }
    return repository.findAll();
  }

  public Order getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("No Order with id="+ id));
  }

  public Order update(Order entity) {
    this.getById(entity.getId());
    return repository.save(entity);
  }

  public void deleteById(Long id) {
    Order foundOrder = this.getById(id);
    repository.deleteById(foundOrder.getId());
  }
}
