package ru.netology.springboot_rest_authorizationservice.service;

import org.springframework.stereotype.Service;
import ru.netology.springboot_rest_authorizationservice.authorities.Authorities;
import ru.netology.springboot_rest_authorizationservice.repository.UserRepository;
import ru.netology.springboot_rest_authorizationservice.exception.InvalidCredentials;
import ru.netology.springboot_rest_authorizationservice.exception.UnauthorizedUser;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String login, String password) {
        if (isEmpty(login) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(login, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + login);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<Authorities> str) {
        return str == null || str.isEmpty();
    }
}
