package com.PizzaBros.PizzaBroRESTBackend.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}