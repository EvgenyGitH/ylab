/*
package ylab;

import io.ylab.model.User;
import io.ylab.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    List<User> userList = new ArrayList<>();

    @Test
    public void addUserTest() {
        User user = new User("Bob", "tester", "bobpassword");
        when(userList.add(user))
                .thenReturn(true);
        userService.addUser(user);
        //    Assertions.assertEquals(1, userList.size());

        assertThat(userService.addUser(user)).isEqualTo(true);
    }


}
*/
