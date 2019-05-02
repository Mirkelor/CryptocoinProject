package com.mirkelor.cryptocurrencyapiproject.service;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.user.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUsername(String username);

    public User findByEmail(String email);

    public void save(UserRegistrationDto userRegistrationDto);
}
