package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Registration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.generator.DataGenerator.*;

public class RegistrationTest {

    @BeforeEach
    void setUp(){
        open("http://localhost:9999");
    }

    @Test
    void shouldReturnSuccessMessageIfRegisteredAndActiveUser() {
        Registration user = registeredActiveUser();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("h2").shouldHave(exactText("Личный кабинет"));
    }

    @Test
    void shouldReturnAlertMessageIfRegisteredAndBlockedUser() {
        Registration user = registeredBlockedUser();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(".notification__content").shouldHave(exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldReturnAlertMessageIfRegisteredAndWrongLoginUser() {
        Registration user = registeredUserWithIncorrectLogin();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldReturnAlertMessageIfRegisteredAndWrongPasswordUser() {
        Registration user = registeredUserWithIncorrectPassword();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }

}