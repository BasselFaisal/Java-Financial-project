package com.financial.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
