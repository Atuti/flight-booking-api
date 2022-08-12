package org.atuti.mokaya.booking.resource;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookingResourceTest {

    // @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/bookings")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}