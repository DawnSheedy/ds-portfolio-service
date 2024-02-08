package com.dawnsheedy.model.site;

import com.dawnsheedy.model.site.page.Page;
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
    public List<Page> pages;

    public Site() {
        this.siteMeta = new SiteMeta();
        this.securitySettings = new SiteSecuritySettings();
        this.pages = new ArrayList<>();
    }

    public void insertPage(Page page) {
        int foundPageIndex = findPageIndexBySlug(page.slug);
        if (foundPageIndex < 0) {
            pages.add(page);
        } else {
            pages.set(foundPageIndex, page);
        }
        update();
    }

    private int findPageIndexBySlug(String slug) {
        for (int i=0; i<pages.size(); i++) {
            Page searchPage = pages.get(i);
            if (searchPage.slug.equals(slug)) {
                return i;
            }
        }
        return -1;
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
