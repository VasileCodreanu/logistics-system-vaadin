package com.cedacri.vaadinlogistics.service;

import com.cedacri.vaadinlogistics.model.Dispatcher;
import com.cedacri.vaadinlogistics.model.Order;
import com.cedacri.vaadinlogistics.model.Vehicle;
import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import com.cedacri.vaadinlogistics.model.baseEntity.Employee;
import com.cedacri.vaadinlogistics.repository.DispatcherRepository;
import com.cedacri.vaadinlogistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SalaryCalculator {

  private final DispatcherRepository dispatcherRepository;

  public BigDecimal calculateSalary(Dispatcher dispatcher) {//Employee emp
    return calculateDispatcherSalary(dispatcher);
  }

  private BigDecimal calculateDispatcherSalary(Dispatcher dispatcher) {

    BigDecimal percentageDispatcherGets = new BigDecimal("0.025");

    List<Order> ordersDoneByDispatcher = dispatcherRepository.getOrdersByDispatcherId(
        dispatcher.getId());

    BigDecimal totalMoneyPaidForOrders = ordersDoneByDispatcher
        .stream()
        .filter(order -> Objects.equals(order.getOrderStatus().toString(), "DELIVERED"))
        .map(Order::getAmountToBePaid)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return totalMoneyPaidForOrders.multiply(percentageDispatcherGets,
        new MathContext(7));
  }
}
