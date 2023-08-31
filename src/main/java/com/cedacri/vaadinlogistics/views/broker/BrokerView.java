package com.cedacri.vaadinlogistics.views.broker;

import com.cedacri.vaadinlogistics.model.Broker;
import com.cedacri.vaadinlogistics.service.BrokerService;
import com.cedacri.vaadinlogistics.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Broker")
@Route(value = "broker", layout = MainLayout.class)
public class BrokerView extends VerticalLayout {

    public BrokerView(BrokerService service) {

        GridCrud<Broker> crud = new GridCrud<>(Broker.class, service);
        add(crud);
        setSizeFull();

    }

}
