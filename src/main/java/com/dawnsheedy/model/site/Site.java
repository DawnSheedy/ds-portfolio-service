package com.dawnsheedy.model.site;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.ws.rs.NotFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

@MongoEntity(collection="siteMeta")
public class Site extends PanacheMongoEntity {
    public ObjectId ownerId;
    public SiteMeta siteMeta;
    public SiteSecuritySettings securitySettings;
    @JsonIgnore
    public List<WorkHistoryEntry> workHistory;
    @JsonIgnore
    public List<SiteSection> sections;


    public Site() {
        this.siteMeta = new SiteMeta();
        this.securitySettings = new SiteSecuritySettings();
        this.workHistory = new ArrayList<>();
        this.sections = new ArrayList<>();
    }

    public static Site findById(String sessionId) throws NotFoundException {
        Site resolvedSite = Site.findById(new ObjectId(sessionId));
        if (resolvedSite == null) {
            throw new NotFoundException();
        }
        return resolvedSite;
    }

    /**
     * Get list of sites by owner
     * @param ownerId
     * @return
     */
    public static List<Site> findByOwnerId(ObjectId ownerId) {
        return list("_id", ownerId);
    }
}
