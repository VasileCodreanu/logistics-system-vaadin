package com.cedacri.vaadinlogistics.views.dispatcher;

import com.cedacri.vaadinlogistics.model.Dispatcher;
import com.cedacri.vaadinlogistics.service.DispatcherService;
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

@PageTitle("Dispatcher")
@Route(value = "dispatcher", layout = MainLayout.class)
public class DispatcherView extends VerticalLayout {

        GridCrud<Dispatcher> crud;

        public DispatcherView(DispatcherService service) {
            crud = new GridCrud<>(Dispatcher.class);

            gridConfiguration();
            formConfiguration();
            setCrudListener(service);
            add(crud);
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
                    backend.deleteById(entity.getId());;
                }
            });
        }


}
