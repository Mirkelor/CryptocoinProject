package com.mirkelor.cryptocurrencyapiproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class CryptocurrencyApiProjectApplication{

    @Autowired
    private Consumer consumer;

    public static void main(String[] args) {

        SpringApplication.run(CryptocurrencyApiProjectApplication.class, args);
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void run(){

        consumer.load();
        consumer.save();
    }

}
