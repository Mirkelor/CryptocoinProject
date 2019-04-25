package com.mirkelor.cryptocurrencyapiproject.controller;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import com.mirkelor.cryptocurrencyapiproject.service.CryptocoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CryptoController {

    private CryptocoinService cryptocoinService;

    public CryptoController(CryptocoinService cryptocoinService) {
        this.cryptocoinService = cryptocoinService;
    }

    @GetMapping("/list")
    public List<Cryptocoin> list(){

        return cryptocoinService.list();
    }
}
