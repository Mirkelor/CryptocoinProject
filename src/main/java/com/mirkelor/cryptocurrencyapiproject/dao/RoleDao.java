package com.mirkelor.cryptocurrencyapiproject.dao;

import com.mirkelor.cryptocurrencyapiproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
