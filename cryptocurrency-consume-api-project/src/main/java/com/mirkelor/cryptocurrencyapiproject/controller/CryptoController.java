package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import com.mirkelor.cryptocurrencyapiproject.service.CryptocoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CryptoController {

    @Autowired
    private CryptocoinService cryptocoinService;

    @RequestMapping("/")
    public String mainPage(){

        return "index";
    }

    @GetMapping("/list")
    public String coinList(HttpServletRequest request, Model model){

        int page = 0; // default page number is 0 (yes it is weird)
        int size = 10; // default page size is 10

        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
           size = Integer.parseInt(request.getParameter("size"));
        }

        // add to the model by get list from database
        model.addAttribute("coinList" ,cryptocoinService.findAll(PageRequest.of(page, size)));

        return "cryptocoins/coin-list.html";
    }

    @GetMapping("/list/details")
    public String coinDetail(@RequestParam("rank") int rank, Model model){

        // get the coin from the service
        Cryptocoin cryptocoin = cryptocoinService.findByRank(rank);

        // set coin as a model attribute to pre-populate the form
        model.addAttribute("cryptocoin", cryptocoin);

        // send over to our form
        return "cryptocoins/coin-detail.html";

    }

}
