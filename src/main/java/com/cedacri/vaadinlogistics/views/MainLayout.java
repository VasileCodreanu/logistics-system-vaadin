package com.cedacri.vaadinlogistics.views;

import com.cedacri.vaadinlogistics.views.broker.BrokerView;
import com.cedacri.vaadinlogistics.views.carrier.CarrierView;
import com.cedacri.vaadinlogistics.views.customer.CustomerView;
import com.cedacri.vaadinlogistics.views.dispatcher.DispatcherView;
import com.cedacri.vaadinlogistics.views.helloworld.HelloWorldView;
import com.cedacri.vaadinlogistics.views.order.OrderView;
import com.cedacri.vaadinlogistics.views.vehicle.VehicleView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Logistics INC");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem(
                "Orders",
                OrderView.class
        ));

        nav.addItem(new SideNavItem(
            "Hello World",
            HelloWorldView.class
        ));
        nav.addItem(new SideNavItem(
            "Broker",
            BrokerView.class
        ));

        nav.addItem(new SideNavItem(
                "Carrier",
                CarrierView.class
        ));
        nav.addItem(new SideNavItem(
                "Customer",
                CustomerView.class
        ));
        nav.addItem(new SideNavItem(
                "Dispatcher",
                DispatcherView.class
        ));
        nav.addItem(new SideNavItem(
                "Vehicles",
                VehicleView.class
        ));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
