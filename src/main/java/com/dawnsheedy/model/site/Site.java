package com.dawnsheedy.model.site;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;

@MongoEntity(collection="siteMeta")
public class Site extends PanacheMongoEntity {
    public ObjectId ownerId;
    public SiteMeta siteMeta;
    public SiteSecuritySettings securitySettings;

    public Site() {
        this.siteMeta = new SiteMeta();
        this.securitySettings = new SiteSecuritySettings();
    }

    public static Site findById(String sessionId) throws NotFoundException {
        Site resolvedSite = Site.findById(new ObjectId(sessionId));
        if (resolvedSite == null) {
            throw new NotFoundException();
        }
        return resolvedSite;
    }
}
