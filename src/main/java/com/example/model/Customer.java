package com.example.model;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonbProperty("CustomerID")
    private String customerId;

    @JsonbProperty("FirstName")
    private String firstName;

    @JsonbProperty("LastName")
    private String lastName;

    @JsonbProperty("Street")
    private String street;

    @JsonbProperty("City")
    private String city;

    @JsonbProperty("Country")
    private String country;

    @JsonbProperty("InternationalAreaCode")
    private String internationalAreaCode;

    public Customer() {
        super();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInternationalAreaCode() {
        return internationalAreaCode;
    }

    public void setInternationalAreaCode(String internationalAreaCode) {
        this.internationalAreaCode = internationalAreaCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(street, customer.street) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(country, customer.country) &&
                Objects.equals(internationalAreaCode, customer.internationalAreaCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, street, city, country, internationalAreaCode);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", internationalAreaCode='" + internationalAreaCode + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
