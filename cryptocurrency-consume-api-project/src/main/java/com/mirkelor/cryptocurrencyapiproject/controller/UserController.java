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

    @RequestMapping("/delete")
    public String deleteUser(Principal principal){

        userService.deleteUserByUsername(principal.getName());

        return "redirect:/logout";
    }
}

    /*TODO
            2-Add mapping for Admin Panel (/admin/panel)
                a-admin should see all registrated user in a list
                    -take all user list from database and list them in /admin/panel
                b-admin should edit user information such as username,email,firstName,lastName,password
                    -upon clicking update button which belongs to user, user profile should be editable in /admin/panel={userid} (by getmapping)
            3-When a user delete once to delete account ask for password confirmation
     */
