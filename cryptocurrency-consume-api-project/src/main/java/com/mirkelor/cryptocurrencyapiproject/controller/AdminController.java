package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/panel")
    public String adminPanel(HttpServletRequest request, Model model){

        int page = 0; // default page number is 0 (yes it is weird)
        int size = 15; // default page size is 10

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
}
