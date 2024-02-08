package com.dawnsheedy;

import com.dawnsheedy.model.core.StatusResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/status")
public class StatusResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StatusResponse getStatus() {
        return new StatusResponse();
    }
}
