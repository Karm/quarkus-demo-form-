package com.example.quarkus;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/hello")
@Singleton
public class HelloController {

    @GET
    @PermitAll
    public String sayHello() {
        return "Hello World";
    }

    @GET
    @RolesAllowed("admin")
    @Path("/admin")
    public String sayHelloToAdmin() {
        return "Hello admin :)";
    }

    @GET
    @RolesAllowed("user")
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHelloToUser(@Context SecurityContext securityContext, @Context Request req) {
        Response.ResponseBuilder rb = Response.ok(securityContext.getUserPrincipal().getName(), MediaType.APPLICATION_JSON);
        return rb.build();
    }
}
