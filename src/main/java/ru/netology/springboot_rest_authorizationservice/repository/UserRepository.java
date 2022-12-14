package ru.netology.springboot_rest_authorizationservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.springboot_rest_authorizationservice.authorities.Authorities;
import ru.netology.springboot_rest_authorizationservice.model.User;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class UserRepository {

    List<User> users;

    public UserRepository() {
        this.users = new CopyOnWriteArrayList<>();
        //добавлен один пользователь для проверки
        users.add(new User("ivan", "ivan2"));
    }

    public List<Authorities> getUserAuthorities(User user) {
        long usersQuantity = users
                .stream().filter(u -> u.getLogin().equals(user.getLogin())
                        && u.getPassword().equals(user.getPassword()))
                .count();

        //в коллекции должен быть только один пользователь с заданным логином и паролем
        if (usersQuantity == 1) {
            return List.of(Authorities.DELETE, Authorities.WRITE, Authorities.READ);
        } else {
            return Collections.emptyList();
        }
    }
}
