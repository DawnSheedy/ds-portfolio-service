package com.dawnsheedy;

import com.dawnsheedy.model.site.ServiceConfig;
import com.dawnsheedy.model.site.ServiceConfigSettings;
import com.dawnsheedy.resource.ServiceConfigResource;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ServiceConfigResourceTest {
    @Inject
    ServiceConfigResource serviceConfigResource;

    @Test
    public void testGetConfig() {
        ServiceConfigSettings settings = serviceConfigResource.getServiceConfigSettings();
        Assertions.assertNotNull(settings);
    }

    @Test
    @TestSecurity(user="testUser", roles = { "manage" })
    public void testSetConfig() {
        ServiceConfigSettings newSettings = new ServiceConfigSettings();
        newSettings.showConstructionPage = true;
        serviceConfigResource.updateServiceConfigSettings(newSettings);
        ServiceConfigSettings settings = serviceConfigResource.getServiceConfigSettings();
        Assertions.assertTrue(settings.showConstructionPage);
    }

    @Test
    public void testSetConfigNotAuthorized() {
        ServiceConfigSettings newSettings = new ServiceConfigSettings();
        newSettings.showConstructionPage = true;
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            serviceConfigResource.updateServiceConfigSettings(newSettings);
        });

        ServiceConfigSettings settings = serviceConfigResource.getServiceConfigSettings();
        Assertions.assertTrue(settings.showConstructionPage);
    }
}
