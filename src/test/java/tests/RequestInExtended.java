package tests;

import model.lombok.LoginBodyLombokModel;
import model.lombok.LoginResponseLombokModel;
import model.pojo.LoginBodyModel;
import model.pojo.LoginResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


public class RequestInExtended {

    @Test
    void loginTestFirst() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void loginTest() {
        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel loginResponse = given()
                .log().uri()
                .contentType(JSON)
                .log().body()
                .body(bodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        Assertions.assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginTestLombok() {
        LoginBodyLombokModel bodyModel = new LoginBodyLombokModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .log().uri()
                .contentType(JSON)
                .log().body()
                .body(bodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        Assertions.assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}
