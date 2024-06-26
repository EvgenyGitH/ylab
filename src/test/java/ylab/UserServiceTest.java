package ylab;

import io.ylab.service.UserService;
import io.ylab.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    @DisplayName("Добавление User")
    public void testAddUser() {
        String name = "UserNameTest";
        String login = "loginTest";
        String password = "passwordTest";

        userService.addUser(name, login, password);

        assertThat(userService.getUserMap()).containsKey(login);
        assertThat(userService.getUserMap().get(login).getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("Добавление User с существующим логином")
    public void testAddUserWithExistingLogin() {
        String name = "UserNameTest";
        String login = "loginTest";
        String password = "passwordTest";

        userService.addUser(name, login, password);
        userService.addUser(name, login, password);

        assertThat(userService.getUserMap().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Авторизация с верными данными")
    public void testAuthorization() {
        String name = "UserNameTest";
        String login = "loginTest";
        String password = "passwordTest";

        userService.addUser(name, login, password);
        boolean isAuthorized = userService.authorization(login, password);

        assertThat(isAuthorized).isTrue();
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testAuthorizationWithWrongPassword() {
        String name = "UserNameTest";
        String login = "loginTest";
        String password = "passwordTest";

        userService.addUser(name, login, password);
        boolean isAuthorized = userService.authorization(login, "TestPassword");

        assertThat(isAuthorized).isFalse();
    }

    @Test
    @DisplayName("Проверка существования логина")
    public void testIsExistsLogin() {
        String name = "UserNameTest";
        String login = "loginTest";
        String password = "passwordTest";
        userService.addUser(name, login, password);
        boolean exists = userService.isExistsLogin(login);
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Проверка отсутствия логина")
    public void testIsNotExistsLogin() {
        boolean exists = userService.isExistsLogin("testLogin");
        assertThat(exists).isFalse();
    }
}





