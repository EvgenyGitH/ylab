package ylab;

import io.ylab.model.User;
import io.ylab.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    List<User> userList;


    @Test
    public void addUserTest() {
        User user = new User("Bob", "tester", "bobpassword");
        userService.addUser(user);
        verify(userList).add(user);
    }


}

