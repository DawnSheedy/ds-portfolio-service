package com.dawnsheedy.model.site;

import org.bson.types.ObjectId;

import jakarta.annotation.Nullable;

public class ServiceConfigSettings {
    @Nullable
    public ObjectId activeSiteId;
    public boolean showConstructionPage;

    public ServiceConfigSettings() {
        showConstructionPage = false;
    }
}
