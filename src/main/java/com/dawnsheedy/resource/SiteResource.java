package com.dawnsheedy.resource;

import com.dawnsheedy.bean.RequestContext;
import com.dawnsheedy.model.site.Site;
import com.dawnsheedy.model.site.SiteMeta;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/site")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SiteResource {
    @Inject
    RequestContext requestContext;

    @POST
    @Authenticated
    public Site createSite(SiteMeta meta) {
        Site newSite = new Site();
        newSite.siteMeta = meta;
        newSite.ownerId = requestContext.getUser().id;
        newSite.persist();
        return newSite;
    }

    @GET
    @Path("/{siteId}/meta")
    public SiteMeta getSiteMeta(String siteId) {
        return requestContext.getSiteMeta().siteMeta;
    }
}
