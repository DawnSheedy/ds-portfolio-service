package com.dawnsheedy.util;

import com.dawnsheedy.bean.RequestContext;
import com.dawnsheedy.model.identity.User;
import com.dawnsheedy.model.site.Site;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.util.List;
import java.util.Optional;

public class RequestFilters {
    private static final Logger LOG = Logger.getLogger(RequestFilters.class);

    @Inject
    RequestContext sessionContext;

    @ServerRequestFilter(preMatching = true)
    public Optional<RestResponse<Void>> createOrGetUser(ContainerRequestContext ctx) {
        if (ctx.getSecurityContext().getUserPrincipal() != null) {
            String userId = ctx.getSecurityContext().getUserPrincipal().getName();
            User foundUser = User.findByAuth0Id(userId);
            if (foundUser == null) {
                LOG.info("Creating new user object for " + userId);
                foundUser = new User();
                foundUser.auth0Identifier = userId;
                foundUser.persist();
            }
            sessionContext.setUser(foundUser);
        }
        return Optional.empty();
    }

    @ServerRequestFilter
    public Optional<RestResponse<Void>> filterSiteAccess(ContainerRequestContext ctx) {
        List<String> siteIds = ctx.getUriInfo().getPathParameters().get("siteId");
        // Abort security check if no sessionId specified or consumer is not a user
        if (siteIds == null) {
            return Optional.empty();
        }

        Site site = Site.findById(siteIds.getFirst());
        sessionContext.setSite(site);

        // Auth is needed only if site is not public or the request is not a GET request
        boolean authNeeded = !site.securitySettings.isPublic || !ctx.getMethod().equals("GET");

        if (authNeeded && ctx.getSecurityContext().isUserInRole("user") && (site.ownerId != null && site.ownerId.equals(new ObjectId(ctx.getSecurityContext().getUserPrincipal().getName())))) {
            LOG.info("Blocked illegal access to site "+site.id);
            return Optional.of(RestResponse.status(Response.Status.FORBIDDEN));
        }

        return Optional.empty();
    }
}
