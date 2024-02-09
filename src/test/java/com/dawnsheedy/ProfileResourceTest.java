package com.dawnsheedy;

import com.dawnsheedy.bean.RequestContext;
import com.dawnsheedy.model.identity.Profile;
import com.dawnsheedy.model.identity.User;
import com.dawnsheedy.resource.ProfileResource;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProfileResourceTest {
    @Inject
    ProfileResource profileResource;

    @Inject
    RequestContext requestContext;

    private User testUser;

    @BeforeEach
    public void setupTestUser() {
        testUser = new User();
        testUser.auth0Identifier = "testUser";
        testUser.profile.displayName = "Test User";
        testUser.persist();
    }

    @Test
    public void testGetCurrentUserNotAuthenticated() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            profileResource.getAuthenticatedUserProfile();
        });
    }

    @Test
    public void testUpdateProfileNotAuthenticated() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            profileResource.updateAuthenticatedUserProfile(new Profile());
        });
    }

    @Test
    public void testGetArbitraryUserNotAuthenticated() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            profileResource.getUserProfile(testUser.id.toString());
        });
    }

    @Test
    @TestSecurity(user = "testUser")
    public void testGetProfile() {
        requestContext.setUser(testUser);
        Profile testProfile = profileResource.getAuthenticatedUserProfile();
        Assertions.assertEquals(testProfile.displayName, testUser.profile.displayName);
    }
}
