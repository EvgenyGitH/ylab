package io.ylab.service;

import io.ylab.model.User;

import java.util.Map;

/**
 * interface UserService для работы с пользователями.
 * Позволяет создавать пользователей, выполнять аутентификацию и проверять существование пользователей.
 */

public interface UserService {
    /**
     * Возвращает список(Map) пользователей.
     * @return Map, где ключом логин пользователя, значение - User.
     */
    Map<String, User> getUserMap();

    /**
     * Генерирует id пользователя.
     * @return id пользователя.
     */
    int createId();

    /**
     * Добавление пользователя.
     * @param name имя пользователя.
     * @param login логин пользователя.
     * @param password пароль пользователя.
     */

    void addUser(String name, String login, String password);

    /**
     * Авторизация пользователя
     * @param login логин
     * @param password пароль
     * @return true - соответствует, false - не соответствует.
     */

    boolean authorization(String login, String password);

    /**
     * Проверка, существует ли пользователь с указанным логином
     * @param login логин
     * @return true, если существует, false не существует.
     */
    boolean isExistsLogin(String login);


}
