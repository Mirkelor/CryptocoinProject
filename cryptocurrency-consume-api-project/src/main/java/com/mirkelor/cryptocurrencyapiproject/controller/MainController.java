package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import com.mirkelor.cryptocurrencyapiproject.service.CryptocoinService;
import com.mirkelor.cryptocurrencyapiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private CryptocoinService cryptocoinService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){

        return "entries/login";
    }

    // add request mapping for /access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied(){

        return "entries/access-denied";
    }

    @GetMapping("/list")
    public String coinList(HttpServletRequest request, Model model, Principal principal){

        page(request, model);

        if(request.getUserPrincipal() != null){

            model.addAttribute("favList", userService.findByUsername(principal.getName()).getFavorites());
        }

        return "cryptocoins/coin-list";
    }

    @GetMapping("/list/details")
    public String coinDetail(@RequestParam("rank") int rank, Model model, HttpServletRequest request, Principal principal){

        // get the coin from the service
        Cryptocoin cryptocoin = cryptocoinService.findByRank(rank);

        // set coin as a model attribute to pre-populate the form
        model.addAttribute("cryptocoin", cryptocoin);

        if(request.getUserPrincipal() != null){

            model.addAttribute("favList", userService.findByUsername(principal.getName()).getFavorites());
        }

        // send over to our form
        return "cryptocoins/coin-detail";

    }

    @RequestMapping("/list/search")
    public String search(@RequestParam("search") String search, HttpServletRequest request, Model model, Principal principal){

        model.addAttribute("search", search);

        int page = 0; // default page number is 0 (yes it is weird)
        int size = 15; // default page size is 15

        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("coinList" ,cryptocoinService.findBySearch(search ,PageRequest.of(page, size)));

        if(request.getUserPrincipal() != null){

            model.addAttribute("favList", userService.findByUsername(principal.getName()).getFavorites());
        }

        return "cryptocoins/coin-list";
    }

    @RequestMapping("/list/fav")
    public String favorite(@RequestParam("rank") int rank, Principal principal, HttpServletRequest request, Model model) {

        userService.addFavorite(principal.getName(), rank);

        page(request, model);

        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/user/favorites")
    public String userFavorites(HttpServletRequest request, Model model, Principal principal){

        page(request, model);

        if(request.getUserPrincipal() != null){

            model.addAttribute("favList", userService.findByUsername(principal.getName()).getFavorites());
        }

        return "cryptocoins/coin-list";
    }

    public void page(HttpServletRequest request, Model model){
        int page = 0; // default page number is 0 (yes it is weird)
        int size = 15; // default page size is 15

        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("coinList" ,cryptocoinService.findAll(PageRequest.of(page, size)));
    }

}
