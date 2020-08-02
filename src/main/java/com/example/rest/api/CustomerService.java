package com.example.rest.api;

import com.example.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/customer")
public interface CustomerService {

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getCustomer(@PathParam("id") String customerId);

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response createCustomer(Customer customer);

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response changeCustomer(Customer customer);

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteCustomer(@PathParam("id") String customerId);

    @Path("/deactivateCustomer/{id}")
    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    Response deactivateCustomer(@PathParam("id") String customerId);

    @Path("/activateCustomer/{id}")
    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    Response activateCustomer(@PathParam("id") String customerId);


}
