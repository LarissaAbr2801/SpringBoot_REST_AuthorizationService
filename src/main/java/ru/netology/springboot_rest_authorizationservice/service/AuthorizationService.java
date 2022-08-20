package ru.netology.springboot_rest_authorizationservice.service;

import org.springframework.stereotype.Service;
import ru.netology.springboot_rest_authorizationservice.authorities.Authorities;
import ru.netology.springboot_rest_authorizationservice.model.User;
import ru.netology.springboot_rest_authorizationservice.repository.UserRepository;
import ru.netology.springboot_rest_authorizationservice.exception.UnauthorizedUser;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User user) {
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user.getLogin());
        }
        return userAuthorities;
    }

    private boolean isEmpty(List<Authorities> str) {
        return str == null || str.isEmpty();
    }
}
