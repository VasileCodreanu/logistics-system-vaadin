package com.cedacri.vaadinlogistics.views.broker;

import com.cedacri.vaadinlogistics.model.Broker;
import com.cedacri.vaadinlogistics.service.BrokerService;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.Collection;

@PageTitle("Broker")
@Route(value = "broker", layout = MainLayout.class)
public class BrokerView extends VerticalLayout {

    GridCrud<Broker> crud;

    public BrokerView(BrokerService service) {
        crud = new GridCrud<>(Broker.class);

        gridConfiguration();
        formConfiguration();
        setCrudListener(service);
        add(crud);
    }

    private void gridConfiguration() {
        crud.addClassName("broker-grid");
        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setPageSize(50);
        crud.getCrudFormFactory().setUseBeanValidation(true);//java Bean Validation
        crud.setSizeFull();
    }

    private void formConfiguration() {
        crud.getGrid().setColumns("name", "email", "phoneNr", "rating");
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "name", "email", "phoneNr", "rating");
    }

    private void setCrudListener(BrokerService backend) {
        crud.setCrudListener(new CrudListener<>() {
            @Override
            public Collection<Broker> findAll() {
                return backend.getAll();
            }

            @Override
            public Broker add(Broker entity) {
                return backend.save(entity);
            }

            @Override
            public Broker update(Broker entity) {
                return backend.update(entity);
            }

            @Override
            public void delete(Broker entity) {
                backend.deleteById(entity.getId());;
            }
        });
    }
}
