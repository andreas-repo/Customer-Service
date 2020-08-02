package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Customer> customerDataList = new ArrayList<>();

    public CustomerData() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Customer> getCustomerDataList() {
        return customerDataList;
    }

    public void setCustomerDataList(List<Customer> customerDataList) {
        this.customerDataList = customerDataList;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "customerDataList=" + customerDataList +
                '}';
    }
}
