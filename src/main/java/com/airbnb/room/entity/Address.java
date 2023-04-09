package com.airbnb.room.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String zipcode;
    private String address1;
    private String address2;
}
