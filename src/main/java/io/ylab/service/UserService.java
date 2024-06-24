package io.ylab.service;

import io.ylab.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private int id = 1;
    public List<User> userList = new ArrayList<>();

    protected int creatId() {
        return id++;
    }

    public void addUser(User user) {
        if (!isExistsLogin(user)) {
            user.setId(creatId());
            userList.add(user);
            System.out.println("Пользователь зарегистрирован, userId: " + user.getId());
        } else {
            System.out.println("Пользователь не зарегистрирован");
        }
    }


    public boolean authorization(String login, String password) {
        boolean result = false;
        for (User user : userList) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    result = true;
                    break;
                } else {
                    System.out.println("Не соответствует Пароль");
                    result = false;
                    break;
                }
            } else {
                result = false;
            }
        }
        return result;
    }


    public boolean isExistsLogin(User user) {
        boolean result = false;
        if(userList.size()==0){
            return result;
        }
        for (User userInList : userList) {
            if (userInList.getLogin().equals(user.getLogin())) {
                System.out.println("Пользователь с указанным Логином существует");
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isExistsByUserId(int userId) {
        boolean result = false;
        for (User user : userList) {
            if (user.getId() == userId) {
                System.out.println("Пользователь с указанным Id существует");
                result = true;
            }
        }
        return result;
    }

}
