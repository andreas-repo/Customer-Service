package com.example;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Customer Service",
                description = "Service which handles customer data",
                version = "1.0.0",
                contact = @Contact (
                        name = "Andreas Mayer",
                        email = "andreas.m4020@gmail.com",
                        url = "http://www.gepardec.com"
                )
        ),
        tags = {
                @Tag(name = "Customer Service", description = "Customer Service operations.")
        },
        servers = {
                @Server (
                        url = "http://{host}/customer-service/",
                        description = "REST endpoint for Customer Service",
                        variables = {
                                @ServerVariable(name = "host",
                                enumeration = {
                                        "example.com"
                                },
                                defaultValue = "example.com",
                                description = "Customer Service host"
                                ),
                        }
                )
        }
)

@ApplicationPath("/customer-service")
public class CustomerServiceApplication extends Application {
}
