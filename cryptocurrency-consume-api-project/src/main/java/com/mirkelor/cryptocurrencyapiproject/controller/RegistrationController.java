package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.service.UserService;
import com.mirkelor.cryptocurrencyapiproject.user.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String showRegistration(Model model){

        model.addAttribute("userRegistrationDto", new UserRegistrationDto());

        return "entries/registration-form";
    }

    @PostMapping
    public String processRegistrationForm(
            @Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
            BindingResult bindingResult,
            Model model){

        String username = userRegistrationDto.getUsername();
        String email = userRegistrationDto.getEmail();
        logger.info("Processing registration form for: " + username);



        // check the database if user already exists
        User existingUser = userService.findByUsername(username);
        User existingEmail = userService.findByEmail(email);
        if (existingUser != null){
            bindingResult.rejectValue("username", null, "There is already an account registered with that username");
            logger.warning("Username already exists.");

        }
        if (existingEmail != null){
            bindingResult.rejectValue("email", null, "There is already an account registered with that email");
            logger.warning("Email already exists.");
        }
        // form validation
        if(bindingResult.hasErrors()){
            return "entries/registration-form";
        }

        // save user in the database
        userService.save(userRegistrationDto);

        logger.info("Successfully created user: " + username);

        return "redirect:/registration?success";
    }
}
