package com.cedacri.vaadinlogistics.views.carrier;

import com.cedacri.vaadinlogistics.model.Carrier;
import com.cedacri.vaadinlogistics.service.CarrierService;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.Collection;

@PageTitle("Carrier")
@Route(value = "carrier", layout = MainLayout.class)
public class CarrierView extends VerticalLayout {

    GridCrud<Carrier> crud;

    public CarrierView(CarrierService service) {
        crud = new GridCrud<>(Carrier.class);

        gridConfiguration();
        formConfiguration();
        setCrudListener(service);
        add(crud);
    }

    private void gridConfiguration() {
        crud.addClassName("carrier-grid");
        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setPageSize(50);
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.setSizeFull();
    }

    private void formConfiguration() {
        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getGrid().setColumns("name", "email", "address");
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "name", "email", "address");
    }

    private void setCrudListener(CarrierService backend) {
        crud.setCrudListener(new CrudListener<>() {
            @Override
            public Collection<Carrier> findAll() {
                return backend.getAll();
            }

            @Override
            public Carrier add(Carrier entity) {
                return backend.save(entity);
            }

            @Override
            public Carrier update(Carrier entity) {
                return backend.update(entity);
            }

            @Override
            public void delete(Carrier entity) {
                backend.delete(entity);
            }
        });
    }
}
