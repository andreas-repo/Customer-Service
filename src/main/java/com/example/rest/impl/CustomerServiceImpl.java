package com.example.rest.impl;

import com.example.model.Customer;
import com.example.model.CustomerData;
import com.example.rest.api.CustomerService;
import com.example.util.CustomerDataDeserializer;
import com.example.util.CustomerDataSerializer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDataDeserializer customerDataDeserializer = new CustomerDataDeserializer();
    private final CustomerDataSerializer customerDataSerializer = new CustomerDataSerializer();

    private CustomerData customerData;

    @Inject
    Logger logger;


    @Override
    public Response getCustomer(String customerId) {
        Response response = Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        try {
            customerData = customerDataDeserializer.doDeserialization(customerData);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Logger.Level.ERROR, "Deserialization error!");
            e.printStackTrace();
        }
        for (Customer cust :
                customerData.getCustomerDataList()
        ) {

            if(cust.getCustomerId().equals(customerId)) {
                response = Response.ok(cust.toString()).status(Response.Status.OK.getStatusCode()).build();
            }

        }
        return response;
    }

    @Override
    public Response createCustomer(Customer customer) {
        Response response = Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        try {
            customerData = customerDataDeserializer.doDeserialization(customerData);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(customerData == null) {
            customerData = new CustomerData();
            List<Customer> customerList = customerData.getCustomerDataList();
            customerList.add(customer);
            customerData.setCustomerDataList(customerList);
            try {
                customerDataSerializer.doSerialization(customerData);
                response = Response.ok("Successful customer created and saved!").status(Response.Status.OK.getStatusCode()).build();

            } catch (IOException e) {
                logger.log(Logger.Level.ERROR, "Serialization error!");
                e.printStackTrace();
            }
        } else {
            try {
                customerData = customerDataDeserializer.doDeserialization(customerData);
                List<Customer> customerList = customerData.getCustomerDataList();
                boolean check = true;
                for (Customer cust :
                        customerList) {

                    if(cust.getCustomerId().equals(customer.getCustomerId())) {
                        check = false;
                        break;
                    }

                }
                if(check) {
                    customerList.add(customer);
                    customerData.setCustomerDataList(customerList);
                    customerDataSerializer.doSerialization(customerData);
                    response = Response.ok("Successful customer created and saved!").status(Response.Status.OK.getStatusCode()).build();
                } else if (check == false) {
                    response = Response.status(Response.Status.CONFLICT.getStatusCode()).build();
                }

            } catch (IOException e) {
                logger.log(Logger.Level.ERROR, "Deserialization error!");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                logger.log(Logger.Level.ERROR, "Class not found error!");
                e.printStackTrace();
            }
        }

        return response;
    }

    @Override
    public Response changeCustomer(String customerId, Customer customer) {
        Response response = Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        try {
            customerData = customerDataDeserializer.doDeserialization(customerData);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Logger.Level.ERROR, "Deserialization error!");
            e.printStackTrace();
        }
        List<Customer> customerList = new ArrayList<>();
        for (Customer cust :
                customerData.getCustomerDataList()) {

            if (cust.getCustomerId().equals(customerId)) {
                cust = customer;
                response = Response.ok("Customer successful changed! \n ").status(Response.Status.OK.getStatusCode()).build();
                break;
            }
            customerList.add(cust);
        }
        customerData.setCustomerDataList(customerList);
        try {
            customerDataSerializer.doSerialization(customerData);
        } catch (IOException e) {
            logger.log(Logger.Level.ERROR, "Serialization failed!");
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteCustomer(String customerId) {
        return null;
    }

    @Override
    public Response deleteAllCustomers() {
        Response response = Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        customerData = null;
        try {
            customerDataSerializer.doSerialization(customerData);
            response = Response.ok("All customers successful deleted!").status(Response.Status.NO_CONTENT.getStatusCode()).build();
        } catch (IOException e) {
            logger.log(Logger.Level.ERROR, "Serialization failed!");
            e.printStackTrace();
        }
        return response;
    }
}
