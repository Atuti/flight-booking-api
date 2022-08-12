package org.atuti.mokaya.passenger.resource;

import io.quarkus.test.junit.QuarkusTest;
// import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PassengerResourceTest {

    // @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/passenger/v1")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}