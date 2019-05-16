package com.mirkelor.cryptocurrencyapiproject.dao;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Transactional
    void deleteUserByUsername(String username);

    @Query("Select u from User u where u.username like %?1% or u.email like %?1% or " +
            "u.firstName like %?1% or u.lastName like %?1%")
    Page<User> findBySearch(String search, Pageable pageable);
}
