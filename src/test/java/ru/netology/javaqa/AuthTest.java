package ru.netology.javaqa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.javaqa.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.javaqa.DataGenerator.Registration.getUser;
import static ru.netology.javaqa.DataGenerator.getRandomLogin;
import static ru.netology.javaqa.DataGenerator.getRandomPassword;

class AuthTest {

        @BeforeEach
        void setup() {
            open("http://localhost:9999");
        }

        @Test
        @DisplayName("Should successfully login with active registered user")
        void shouldSuccessfulLoginIfRegisteredActiveUser() {
            var registeredUser = getRegisteredUser("active");
            $("[data-test-id=login").setValue(registeredUser.getLogin());
            $("[data-test-id=password").setValue(registeredUser.getPassword());
            $(".button").click();
            $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);

            // TODO: добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
            //  данными зарегистрированного активного пользователя, для заполнения полей формы используйте
            //  пользователя registeredUser
        }

        @Test
        @DisplayName("Should get error message if login with not registered user")
        void shouldGetErrorIfNotRegisteredUser() {
            var notRegisteredUser = getUser("active");
            $("[data-test-id=login").setValue(notRegisteredUser.getLogin());
            $("[data-test-id=password").setValue(notRegisteredUser.getPassword());
            $(".button").click();
           // $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);

            // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет
            //  незарегистрированного пользователя, для заполнения полей формы используйте пользователя notRegisteredUser
        }

        @Test
        @DisplayName("Should get error message if login with blocked registered user")
        void shouldGetErrorIfBlockedUser() {
            var blockedUser = getRegisteredUser("blocked");
            $("[data-test-id=login").setValue(blockedUser.getLogin());
            $("[data-test-id=password").setValue(blockedUser.getPassword());
            $(".button").click();
           // $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
            // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
            //  заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
        }

        @Test
        @DisplayName("Should get error message if login with wrong login")
        void shouldGetErrorIfWrongLogin() {
            var registeredUser = getRegisteredUser("active");
            var wrongLogin = getRandomLogin();
            $("[data-test-id=login").setValue(wrongLogin);
            $("[data-test-id=password").setValue(registeredUser.getPassword());
            $(".button").click();
           // $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
            // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
            //  логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
            //  "Пароль" - пользователя registeredUser
        }

        @Test
        @DisplayName("Should get error message if login with wrong password")
        void shouldGetErrorIfWrongPassword() {
            var registeredUser = getRegisteredUser("active");
            var wrongPassword = getRandomPassword();
            $("[data-test-id=login").setValue(registeredUser.getLogin());
            $("[data-test-id=password").setValue(wrongPassword);
            $(".button").click();
            //$("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
            // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
            //  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
            //  "Пароль" - переменную wrongPassword
        }
    }

