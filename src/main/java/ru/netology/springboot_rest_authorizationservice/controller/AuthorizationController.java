package ru.netology.springboot_rest_authorizationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springboot_rest_authorizationservice.authorities.Authorities;
import ru.netology.springboot_rest_authorizationservice.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("login") String login,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(login, password);
    }
}
