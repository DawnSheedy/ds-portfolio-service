package com.dawnsheedy.model.site;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class ServiceConfig extends PanacheMongoEntity {
    public ServiceConfigSettings settings;

    public ServiceConfig() {
        settings = new ServiceConfigSettings();
    }

    /**
     * Find or create the singular serviceConfig
     * @return
     */
    public static ServiceConfig getOrCreateServiceConfig() {
        ServiceConfig resolvedConfig = findAll().firstResult();

        if (resolvedConfig == null) {
            resolvedConfig = new ServiceConfig();
            resolvedConfig.persist();
        }
        
        return resolvedConfig;
    }
}
