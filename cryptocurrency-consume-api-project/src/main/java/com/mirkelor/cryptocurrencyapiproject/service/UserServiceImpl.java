package com.mirkelor.cryptocurrencyapiproject.service;

import com.mirkelor.cryptocurrencyapiproject.dao.RoleDao;
import com.mirkelor.cryptocurrencyapiproject.dao.UserDao;
import com.mirkelor.cryptocurrencyapiproject.entity.Role;
import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.user.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {

        return userDao.findByEmail(email);
    }

    @Override
    public void deleteUserByUsername(String username){

        userDao.deleteUserByUsername(username);
    }

    @Override
    public void save(UserRegistrationDto userRegistrationDto) {

        User user = new User();

        // assign user details to the user object
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());

        // give user default role of "employees"

        user.setRoles(Arrays.asList(roleDao.findByRole("USER")));

        // save user in the database
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles){

        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
