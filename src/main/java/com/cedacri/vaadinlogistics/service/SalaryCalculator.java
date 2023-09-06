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
    private final VehicleRepository vehicleRepository;

    public BigDecimal calculateSalary(BaseEntity employee){//Employee emp
        return switch (employee){
            case Dispatcher dispatcher -> calculateDispatcherSalary(dispatcher);
            case Vehicle vehicle -> calculateDriverSalary(vehicle);
            default -> {
                yield new BigDecimal("0");
            }
        };
    }

    private BigDecimal calculateDispatcherSalary(Dispatcher dispatcher){

        BigDecimal percentageDispatcherGets = new BigDecimal("0.025");

        List<Order> ordersByDispatcherId = dispatcherRepository.getOrdersByDispatcherId(dispatcher.getId());

        BigDecimal totalMoneyPaidForOrders = ordersByDispatcherId
                .stream()
                .filter(order -> Objects.equals(order.getOrderStatus().toString(), "DELIVERED"))
                .map(Order::getAmountToBePaid)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalMoneyPaidForOrders.multiply(percentageDispatcherGets, new MathContext(2));
    }

    private BigDecimal calculateDriverSalary(Vehicle vehicle){

        BigDecimal percentageVehicleGets = new BigDecimal("0.9");

        List<Order> ordersDoneByVehicle = vehicleRepository.getOrdersDoneByVehicle(vehicle.getId());
        BigDecimal totalMoneyPaidForOrders = ordersDoneByVehicle
                .stream()
                .filter(order -> Objects.equals(order.getOrderStatus().toString(), "DELIVERED"))
                .map(Order::getAmountToBePaid)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalMoneyPaidForOrders.multiply(percentageVehicleGets, new MathContext(2));
    }

}
