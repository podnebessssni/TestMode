package ru.netology.generator;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;
import ru.netology.domain.Registration;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {


    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUpAll(Registration registration) {
        given()
                .spec(requestSpec)
                .body(registration)
                .when() // "когда"
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static Registration registeredActiveUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        val registartion = new Registration(login,password,"active");
        setUpAll(registartion);
        return registartion;
    }

    public static Registration registeredBlockedUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        val registartion = new Registration(login,password,"blocked");
        setUpAll(registartion);
        return registartion;
    }

    public static Registration registeredUserWithIncorrectLogin(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        val registartion = new Registration(login,password,"active");
        setUpAll(registartion);
        return new Registration("Johny", password, "active");
    }
    public static Registration registeredUserWithIncorrectPassword(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        val registartion = new Registration(login,password,"active");
        setUpAll(registartion);
        return new Registration(login, "qwerty", "active");
    }
}