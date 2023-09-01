package com.cedacri.vaadinlogistics.views.order;

import com.cedacri.vaadinlogistics.model.Order;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Order")
@Route(value = "order", layout = MainLayout.class)
public class OrderView extends VerticalLayout{
        GridCrud<Order> crud;

//        public OrderView(OrderDtoService service) {
//            crud = new GridCrud<>(Order.class);
//
//            gridConfiguration();
//            formConfiguration();
//            setCrudListener(service);
//            add(crud);
//        }

        private void gridConfiguration() {
            crud.addClassName("order-grid");
            crud.getGrid().setColumnReorderingAllowed(true);
            crud.getGrid().setPageSize(50);
            crud.getCrudFormFactory().setUseBeanValidation(true);
            crud.setSizeFull();
        }
//        @NotNull
//        @NotBlank
//        private String vehicleNr;
//        @NotNull
//        @NotBlank
//        private String currentCityLocation;
//        @NotNull
//        private Vehicle.VehicleStatus currentStatus;
//        private void formConfiguration() {
//            crud.getGrid().setColumns("vehicleNr", "currentCityLocation", "currentStatus");
//            crud.getCrudFormFactory().setVisibleProperties(
//                    CrudOperation.ADD,
//                    "vehicleNr", "currentCityLocation", "currentStatus");
//        }
//
//        private void setCrudListener(OrderDtoService backend) {
//            crud.setCrudListener(new CrudListener<>() {
//                @Override
//                public Collection<Order> findAll() {
//                    return backend.getAll();
//                }
//
//                @Override
//                public Order add(Order entity) {
//                    return backend.save(entity);
//                }
//
//                @Override
//                public Order update(Order entity) {
//                    return backend.update(entity);
//                }
//
//                @Override
//                public void delete(Order entity) {
//                    backend.deleteById(entity.getId());;
//                }
//            });
//        }


    }
