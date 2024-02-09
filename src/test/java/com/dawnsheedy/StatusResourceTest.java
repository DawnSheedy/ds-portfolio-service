package com.dawnsheedy;

import com.dawnsheedy.model.core.StatusResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class StatusResourceTest {
    @Inject
    StatusResource statusResource;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/ds-portfolio-service/status")
          .then()
             .statusCode(200);
    }

    @Test
    public void altHelloTest() {
        StatusResponse testResponse = statusResource.getStatus();
        Assertions.assertTrue(testResponse.isLive);
    }

}