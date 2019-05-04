package com.mirkelor.cryptocurrencyapiproject.dao;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Transactional
    void deleteUserByUsername(String username);

}
