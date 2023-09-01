package com.cedacri.vaadinlogistics.views.vehicle;

import com.cedacri.vaadinlogistics.model.Customer;
import com.cedacri.vaadinlogistics.model.Vehicle;
import com.cedacri.vaadinlogistics.service.CustomerService;
import com.cedacri.vaadinlogistics.service.VehicleService;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.Collection;

@PageTitle("Vehicle")
@Route(value = "vehicle", layout = MainLayout.class)
public class VehicleView extends VerticalLayout{

        GridCrud<Vehicle> crud;

        public VehicleView(VehicleService service) {
            crud = new GridCrud<>(Vehicle.class);

            gridConfiguration();
            formConfiguration();
            setCrudListener(service);
            add(crud);
        }

        private void gridConfiguration() {
            crud.addClassName("vehicle-grid");
            crud.getGrid().setColumnReorderingAllowed(true);
            crud.getGrid().setPageSize(50);
            crud.getCrudFormFactory().setUseBeanValidation(true);
            crud.setSizeFull();
        }
    @NotNull
    @NotBlank
    private String vehicleNr;
    @NotNull
    @NotBlank
    private String currentCityLocation;
    @NotNull
    private Vehicle.VehicleStatus currentStatus;
        private void formConfiguration() {
            crud.getGrid().setColumns("vehicleNr", "currentCityLocation", "currentStatus");
            crud.getCrudFormFactory().setVisibleProperties(
                    CrudOperation.ADD,
                    "vehicleNr", "currentCityLocation", "currentStatus");
        }

        private void setCrudListener(VehicleService backend) {
            crud.setCrudListener(new CrudListener<>() {
                @Override
                public Collection<Vehicle> findAll() {
                    return backend.getAll();
                }

                @Override
                public Vehicle add(Vehicle entity) {
                    return backend.save(entity);
                }

                @Override
                public Vehicle update(Vehicle entity) {
                    return backend.update(entity);
                }

                @Override
                public void delete(Vehicle entity) {
                    backend.deleteById(entity.getId());;
                }
            });
        }

}
