package com.cedacri.vaadinlogistics.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Address {
    private String city;
    private String address;


}
