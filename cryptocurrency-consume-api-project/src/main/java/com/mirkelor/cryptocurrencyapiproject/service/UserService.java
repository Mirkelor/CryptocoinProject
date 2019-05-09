package com.mirkelor.cryptocurrencyapiproject.service;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.user.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface UserService extends UserDetailsService {

    public User findByUsername(String username);

    public User findByEmail(String email);

    public void save(UserRegistrationDto userRegistrationDto);

    public void deleteUserByUsername(String username);

    public BCryptPasswordEncoder getPasswordEncoder();

    public Page<User> findAll(Pageable pageable);

    public Page<User> findBySearch(String search, Pageable pageable);

    public void addFavorite(String username , int rank);
}
