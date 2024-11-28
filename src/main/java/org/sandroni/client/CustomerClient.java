package org.sandroni.client;

import jakarta.ws.rs.PathParam;
import org.sandroni.dto.CustomerDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/customers")
@RegisterRestClient
@ApplicationScoped
public interface CustomerClient {

    @GET
    @Path("/{id}")
    CustomerDTO getCustomer(@PathParam("id") Long id);
}
