package com.mirkelor.cryptocurrencyapiproject;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import com.mirkelor.cryptocurrencyapiproject.service.CryptocoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Consumer {

    @Autowired
    private CryptocoinService cryptocoinService;

    private List<Cryptocoin> cryptocoinList;

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    public void load(){

        logger.info("Consumer: Loading Api");
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();

        Cryptocoin[] cryptocoins = restTemplate.getForObject
                        ("https://api.coinmarketcap.com/v1/ticker/", Cryptocoin[].class);

        cryptocoinList = Arrays.asList(cryptocoins);
        logger.info("Consumer: Loaded Api successfully");
    }

    public void save(){

        logger.info("Consumer: Saving to database");
        cryptocoinService.saveAll(cryptocoinList);
        logger.info("Consumer: Saved database successfully");
    }

    public List<Cryptocoin> getCryptocoinList() {
        return cryptocoinList;
    }

    public void setCryptocoinList(List<Cryptocoin> cryptocoinList) {
        this.cryptocoinList = cryptocoinList;
    }
}
