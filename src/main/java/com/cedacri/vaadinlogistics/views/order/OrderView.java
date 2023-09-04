package com.cedacri.vaadinlogistics.views.order;

import com.cedacri.vaadinlogistics.model.*;
import com.cedacri.vaadinlogistics.service.*;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import java.util.Collection;

@PageTitle("Order")
@Route(value = "order", layout = MainLayout.class)
public class OrderView extends VerticalLayout{
        GridCrud<Order> crud;

        public OrderView(
                OrderService orderService, BrokerService brokerService,
                CarrierService carrierService, CustomerService customerService,
                DispatcherService dispatcherService, VehicleService vehicleService

        ) {
            crud = new GridCrud<>(Order.class);

            gridConfiguration();
            formConfiguration(brokerService, carrierService, customerService, dispatcherService, vehicleService);
            setCrudListener(orderService);
            add(crud);
        }

        private void gridConfiguration() {
            crud.addClassName("order-grid");
            crud.getGrid().setColumnReorderingAllowed(true);
            crud.getGrid().setPageSize(50);
            crud.getCrudFormFactory().setUseBeanValidation(true);

            crud.getGrid().setColumns("id", "amountToBePaid", "commodity", "orderPlacedOn", "orderDeliveredOn", "orderStatus");
            crud.getGrid().addColumn( order -> order.getBroker().getName() ).setHeader("Broker");
            crud.getGrid().addColumn( order -> order.getCarrier().getName() ).setHeader("Carrier");
            crud.getGrid().addColumn( order -> order.getSender().getName() ).setHeader("Sender");
            crud.getGrid().addColumn( order -> order.getReceiver().getName() ).setHeader("Receiver");
            crud.getGrid().addColumn( order -> order.getDispatcher().getFirstName() ).setHeader("Dispatcher");
            crud.getGrid().addColumn( order -> order.getVehicle().getVehicleNr() ).setHeader("Vehicle");
            crud.getGrid().getColumns().forEach(col -> col.setAutoWidth(true));

            crud.setSizeFull();
        }

        private void formConfiguration(
                BrokerService brokerService,
                CarrierService carrierService, CustomerService customerService,
                DispatcherService dispatcherService, VehicleService vehicleService) {

            crud.getCrudFormFactory().setUseBeanValidation(true);

            crud.getCrudFormFactory().setVisibleProperties(
                    "amountToBePaid", "commodity",
                    "broker", "carrier",
                    "sender", "receiver",
                    "dispatcher", "vehicle"
            );

            crud.getCrudFormFactory().setVisibleProperties(
                    CrudOperation.ADD,
                    "amountToBePaid",  "commodity",
                    "broker", "carrier",
                    "sender", "receiver",
                    "dispatcher", "vehicle"
                    );
            crud.getCrudFormFactory().setFieldProvider("broker",
                    new ComboBoxProvider<>( "Broker",   brokerService.getAll(), new TextRenderer<>(Broker::getName), Broker::getName));
            crud.getCrudFormFactory().setFieldProvider("carrier",
                    new ComboBoxProvider<>("Carrier",   carrierService.getAll(), new TextRenderer<>(Carrier::getName), Carrier::getName));
            crud.getCrudFormFactory().setFieldProvider("sender",
                    new ComboBoxProvider<>("Sender",    customerService.getAll(), new TextRenderer<>(Customer::getName), Customer::getName));
            crud.getCrudFormFactory().setFieldProvider("receiver",
                    new ComboBoxProvider<>("Receivers", customerService.getAll(), new TextRenderer<>(Customer::getName), Customer::getName));
            crud.getCrudFormFactory().setFieldProvider("dispatcher",
                    new ComboBoxProvider<>("Dispatcher", dispatcherService.getAll(), new TextRenderer<>(Dispatcher::getEmail), Dispatcher::getEmail));
            crud.getCrudFormFactory().setFieldProvider("vehicle",
                   new ComboBoxProvider<>("Vehicle",     vehicleService.getAll(), new TextRenderer<>(Vehicle::getVehicleNr), Vehicle::getVehicleNr));
        }

        private void setCrudListener(OrderService backend) {
            crud.setCrudListener(new CrudListener<>() {
                @Override
                public Collection<Order> findAll() {
                    return backend.getAll();
                }

                @Override
                public Order add(Order entity) {
                    return backend.save(entity);
                }

                @Override
                public Order update(Order entity) {
                    return backend.update(entity);
                }

                @Override
                public void delete(Order entity) {
                    backend.deleteById(entity.getId());;
                }
            });
        }
    }
