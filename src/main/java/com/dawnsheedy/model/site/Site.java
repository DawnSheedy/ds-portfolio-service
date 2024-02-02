package com.dawnsheedy.model.site;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="siteMeta")
public class Site extends PanacheMongoEntity {
    public ObjectId ownerId;
    public SiteMeta siteMeta;
    public boolean isPublic;

    public Site() {
        this.siteMeta = new SiteMeta();
        this.isPublic = false;
    }
}
