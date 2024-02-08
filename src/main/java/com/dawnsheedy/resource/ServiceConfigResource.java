package com.dawnsheedy.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Path;

import com.dawnsheedy.model.site.ServiceConfig;
import com.dawnsheedy.model.site.ServiceConfigSettings;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/service-configuration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceConfigResource {
    @GET
    public ServiceConfigSettings getServiceConfigSettings() {
        return ServiceConfig.getOrCreateServiceConfig().settings;
    }

    @PUT
    @RolesAllowed({"manage"})
    public ServiceConfigSettings updateServiceConfigSettings(ServiceConfigSettings updatedSettings) {
        ServiceConfig serviceConfig = ServiceConfig.getOrCreateServiceConfig();
        serviceConfig.settings = updatedSettings;
        serviceConfig.update();
        return serviceConfig.settings;
    }
}
