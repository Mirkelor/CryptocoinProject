package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.service.UserService;
import com.mirkelor.cryptocurrencyapiproject.user.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin/userlist")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String adminPanel(HttpServletRequest request, Model model){

        int page = 0; // default page number is 0 (yes it is weird)
        int size = 10; // default page size is 10

        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }

        // add to the model by get list from database
        model.addAttribute("userList" , userService.findAll(PageRequest.of(page, size)));

        return "admin/admin-panel";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("search") String search, HttpServletRequest request, Model model){

        System.out.println(search);

        int page = 0; // default page number is 0 (yes it is weird)
        int size = 10; // default page size is 10

        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("search", search);

        model.addAttribute("userList" ,userService.findBySearch(search, PageRequest.of(page, size)));

        return "admin/admin-panel";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("username") String username, Model model){

        User user = userService.findByUsername(username);

        System.out.println(user);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(user);

        model.addAttribute("user", user);

        model.addAttribute("userRegistrationDto", userRegistrationDto);

        return "user/user-detail";

    }

    @PostMapping("/edit")
    public String saveUser(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult, Model model){

        User user = userService.findByUsername(userRegistrationDto.getUsername());

        model.addAttribute("user", user);

        if(bindingResult.hasErrors()){
            return "user/user-detail";
        }

        userService.save(userRegistrationDto);

        return "redirect:/admin/userlist";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, @RequestParam String username){

        User user = userService.findByUsername(username);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(user);

        model.addAttribute("user", user);

        model.addAttribute("userRegistrationDto", userRegistrationDto);

        return "user/user-detail";
    }

    @PostMapping("/delete")
    public String processDeleteUser(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                                    Principal principal, BindingResult bindingResult, Model model){

        User controlUser = userService.findByUsername(principal.getName());

        model.addAttribute("user", controlUser);

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
            System.out.println("You want to delete: " + userRegistrationDto.getUsername());
            userService.deleteUserByUsername(userRegistrationDto.getUsername());
            return "redirect:/admin/userlist";
        }

        return "redirect:/user/profile";
    }


}
