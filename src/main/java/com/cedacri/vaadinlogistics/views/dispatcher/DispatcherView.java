package com.cedacri.vaadinlogistics.views.dispatcher;

import com.cedacri.vaadinlogistics.model.Dispatcher;
import com.cedacri.vaadinlogistics.service.DispatcherService;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.Collection;

@PageTitle("Dispatcher")
@Route(value = "dispatcher", layout = MainLayout.class)
public class DispatcherView extends VerticalLayout {

    GridCrud<Dispatcher> crud;
    TextField filterText;

    Button salaryBtn;



//        sayHello.addClickShortcut(Key.ENTER);

//        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//
//        add(name, sayHello);


        public DispatcherView(DispatcherService service) {
            crud = new GridCrud<>(Dispatcher.class);
            filterText = new TextField();

            gridConfiguration();
            formConfiguration();
            setCrudListener(service);
            configureFilter(service);


            salaryBtn = new Button("Get Dispatcher Salary");
            salaryBtn.addClickListener(e -> {
//                Notification.show("Salary  " + name.getValue());
//                gridConfiguration();
                service.calculateSalary();
                salaryFormConfiguration();
            });

            add( new HorizontalLayout(filterText, salaryBtn), crud);
        }

    private void salaryFormConfiguration() {
        crud.getGrid().setColumns("firstName", "lastName", "email", "salary");
    }


    private void configureFilter(DispatcherService service) {
        filterText.setPlaceholder("Filter by first name..");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateView(service));
    }

    private void updateView(DispatcherService service) {
        crud.getGrid().setItems(service.findByName(filterText.getValue()));
    }

        private void gridConfiguration() {
            crud.addClassName("dispatcher-grid");
            crud.getGrid().setColumnReorderingAllowed(true);
            crud.getGrid().setPageSize(50);
            crud.getCrudFormFactory().setUseBeanValidation(true);
            crud.setSizeFull();
        }

        private void formConfiguration() {
            crud.getGrid().setColumns("firstName", "lastName", "email", "phoneNr");
            crud.getCrudFormFactory().setVisibleProperties(
                    CrudOperation.ADD,
                    "firstName", "lastName", "email", "phoneNr");
        }

        private void setCrudListener(DispatcherService backend) {
            crud.setCrudListener(new CrudListener<>() {
                @Override
                public Collection<Dispatcher> findAll() {
                    return backend.getAll();
                }

                @Override
                public Dispatcher add(Dispatcher entity) {
                    return backend.save(entity);
                }

                @Override
                public Dispatcher update(Dispatcher entity) {
                    return backend.update(entity);
                }

                @Override
                public void delete(Dispatcher entity) {
                    backend.deleteById(entity.getId());
                }
            });
        }


}
