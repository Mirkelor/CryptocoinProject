package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.service.CryptocoinService;
import com.mirkelor.cryptocurrencyapiproject.service.UserService;
import com.mirkelor.cryptocurrencyapiproject.user.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public String userDetail(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);


        return "user/user-detail";
    }

    @GetMapping("/edit")
    public String editUser(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(user);

        model.addAttribute("user", user);

        model.addAttribute("userRegistrationDto", userRegistrationDto);

        return "user/user-detail";
    }

    @PostMapping("/edit")
    public String saveUser(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult){

        System.out.println(userRegistrationDto);

        if(bindingResult.hasErrors()){
            return "user/user-detail";
        }

        userService.save(userRegistrationDto);

        return "redirect:/user/profile";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(user);

        model.addAttribute("userRegistrationDto", userRegistrationDto);

        return "user/user-detail";
    }

    @PostMapping("/delete")
    public String processDeleteUser(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                                    Principal principal, BindingResult bindingResult){

        System.out.println(userRegistrationDto);

        User controlUser = userService.findByUsername(principal.getName());

        boolean passControl = userService.getPasswordEncoder().matches(userRegistrationDto.getPassword(), controlUser.getPassword());

        if (!passControl){
            bindingResult.rejectValue("password", null, "The password is wrong");
        }

        if(bindingResult.hasErrors()){
            System.out.println("There is an bindingResult Error");
            return "user/user-detail";
        }

        if(passControl){
            System.out.println("Two passwords are EQUAL process Delete");
            userService.deleteUserByUsername(controlUser.getUsername());
            return "redirect:/logout";
        }

        return "redirect:/user/profile";
    }
}

    /*TODO
           -1-add update and delete button
            0-add user roles to /admin/userlist page
            1-add delete user option to admin panel (protected with admin password)
            2-save user by admin should have email validation
            3-add pageable search bar to /admin/userlist page
     */
    /*FIXME
            1-Price display with E in Coin list
    */
