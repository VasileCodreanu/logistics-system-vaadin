package com.cedacri.vaadinlogistics;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "myapp")
public class VaadinLogisticsApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(VaadinLogisticsApplication.class, args);
	}

}
