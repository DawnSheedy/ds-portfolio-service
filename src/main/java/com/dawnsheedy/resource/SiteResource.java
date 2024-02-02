package com.dawnsheedy.resource;

import com.dawnsheedy.bean.RequestContext;
import com.dawnsheedy.model.site.Site;
import com.dawnsheedy.model.site.SiteMeta;
import com.dawnsheedy.model.site.SiteSecuritySettings;
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

    @POST
    @Path("/{siteId}/security")
    @Authenticated
    public SiteSecuritySettings setSecuritySettings(String siteId, SiteSecuritySettings securitySettings) {
        Site site = this.requestContext.getSite();
        site.securitySettings = securitySettings;
        site.update();
        return site.securitySettings;
    }

    @GET
    @Path("/{siteId}/security")
    @Authenticated
    public SiteSecuritySettings getSecuritySettings(String siteId) {
        return this.requestContext.getSite().securitySettings;
    }

    @POST
    @Path("/{siteId}/meta")
    @Authenticated
    public SiteMeta setSiteMeta(String siteId, SiteMeta newMeta) {
        Site site = this.requestContext.getSite();
        site.siteMeta = newMeta;
        site.update();
        return site.siteMeta;
    }

    @GET
    @Path("/{siteId}/meta")
    public SiteMeta getSiteMeta(String siteId) {
        return requestContext.getSite().siteMeta;
    }
}
