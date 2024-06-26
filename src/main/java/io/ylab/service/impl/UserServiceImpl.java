package io.ylab.service.impl;

import io.ylab.model.User;
import io.ylab.out.Printer;
import io.ylab.service.UserService;

import java.util.HashMap;
import java.util.Map;


public class UserServiceImpl implements UserService {
    private int id = 1;
    public Map<String, User> userMap = new HashMap();
    @Override
    public Map<String, User> getUserMap() {
        return userMap;
    }

    @Override
    public int createId() {
        return id++;
    }

    @Override
    public void addUser(String name, String login, String password) {
        if (!isExistsLogin(login)) {
            User user = new User(name, login, password);
            user.setId(createId());
            userMap.put(login, user);
            Printer.printMessage("Пользователь зарегистрирован, userId: " + user.getId());
        } else {
            Printer.printMessage("Пользователь не зарегистрирован");
        }
    }

    @Override
    public boolean authorization(String login, String password) {
        boolean result = false;
        if (userMap.size() == 0) {
            Printer.printMessage("Пользователь не найден");
            return result;
        }
        User user = userMap.get(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                result = true;
            } else {
                Printer.printMessage("Не соответствует Пароль");
                result = false;
            }
        } else {
            Printer.printMessage("Пользователь не найден");
            result = false;
        }
        return result;
    }

    @Override
    public boolean isExistsLogin(String login) {
        boolean result = false;
        if (userMap.size() == 0) {
            return result;
        }
        User user = userMap.get(login);
        if (user != null) {
            result = true;
            Printer.printMessage("Пользователь с указанным Логином существует");
        }
        return result;
    }


}
