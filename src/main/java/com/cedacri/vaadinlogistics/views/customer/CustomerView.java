package com.cedacri.vaadinlogistics.views.customer;

import com.cedacri.vaadinlogistics.model.Address;
import com.cedacri.vaadinlogistics.model.Customer;
import com.cedacri.vaadinlogistics.service.CustomerService;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.awt.*;
import java.util.Collection;

@PageTitle("Customer")
@Route(value = "customer", layout = MainLayout.class)
public class CustomerView extends VerticalLayout{

    GridCrud<Customer> crud;

    TextField filterText =  new TextField();

    public CustomerView(CustomerService service) {
        crud = new GridCrud<>(Customer.class);

        gridConfiguration();
        formConfiguration();
        setCrudListener(service);
        configureFilter();
        add(filterText, crud);
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name..");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateView());
    }

    private void updateView() {
    }

    private void gridConfiguration() {
        crud.addClassName("customer-grid");
        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setPageSize(50);
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.setSizeFull();
    }

    private void formConfiguration() {
        crud.getGrid().setColumns("name", "email", "proneNr", "directions");
        crud.getGrid().addColumn(o -> {
                    Address address = o.getAddress();
                    return null ==  address ? "-": address.getAddress();
        }).setHeader("address").setKey("addre");
//        crud.getGrid().addColumn(o -> o.getAddress().getCity()).setHeader("city").setKey("ci");
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "name", "email", "proneNr", "directions", "city");
//        crud.getCrudFormFactory().setFieldProvider("address",
//                new ComboBoxProvider<>(service.findAll().stream().map(Broker::getAddress).toList()));
//        crud.getCrudFormFactory().setFieldProvider("groups",
//                new CheckBoxGroupProvider<>(groupService.findAll()));
//        crud.getCrudFormFactory().setFieldProvider("groups",
//                new CheckBoxGroupProvider<>("Groups", groupService.findAll(), Group::getName));
//        crud.getCrudFormFactory().setFieldProvider("mainGroup",
//                new ComboBoxProvider<>("Main Group", groupService.findAll(), new TextRenderer<>(Group::getName), Group::getName));

    }

    private void setCrudListener(CustomerService backend) {
        crud.setCrudListener(new CrudListener<>() {
            @Override
            public Collection<Customer> findAll() {
                return backend.getAll();
            }

            @Override
            public Customer add(Customer entity) {
                return backend.save(entity);
            }

            @Override
            public Customer update(Customer entity) {
                return backend.update(entity);
            }

            @Override
            public void delete(Customer entity) {
                backend.deleteById(entity.getId());;
            }
        });
    }
}

