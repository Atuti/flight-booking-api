package org.atuti.mokaya.booking.resource;

import io.quarkus.test.junit.QuarkusTest;
// import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RouteResourceTest {

    // @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/routes")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}