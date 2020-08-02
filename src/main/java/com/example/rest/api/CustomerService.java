package com.example.rest.api;

import com.example.model.Customer;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Tag(name = "customer")
@Path("/customer")
public interface CustomerService {
    @Operation(operationId = "getCustomer", description = "Returns a customer object.")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "The request is successful executed.",
                    content = @Content(
                            schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "A customer object in string form.",
                                    implementation = Customer.class
                            )
                    )
            )
    })
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") String customerId);

    @Operation(operationId = "createCustomer", description = "Creates a customer object.")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Successful customer creation"

            )
    })
    @RequestBody(content = @Content(
            schema = @Schema(
                    type = SchemaType.OBJECT,
                    description = "A Customer.class object.",
                    required = true,
                    implementation = Customer.class
            )),
            required = true

    )
    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer);

    @Operation(operationId = "changeCustomer", description = "Change a customer object.")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Successful change ob customer object."
            )
    })
    @Path("/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeCustomer(@PathParam("id") String customerId, Customer customer);

    @Operation(operationId = "deleteCustomer", description = "Deletes the customer with the specified customerId.")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Successful deletion of the customer object."
            )
    })
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(String customerId);

    @Operation(operationId = "deleteAllCustomers", description = "Deletes all customer objects.")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Successful deletion of all customer objects."
            )
    })
    @Path("/")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllCustomers();
}
