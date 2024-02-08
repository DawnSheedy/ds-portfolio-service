package com.dawnsheedy.model.core;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class StatusResponse {
    public boolean isLive;
    public String version;
    public String appName;

    public StatusResponse() {
        isLive = true;
        appName = "ds-portfolio-service";
        version = ConfigProvider.getConfig().getValue("quarkus.application.version", String.class);
    }
}
