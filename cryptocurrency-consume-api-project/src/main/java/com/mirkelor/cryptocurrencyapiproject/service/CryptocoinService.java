package com.mirkelor.cryptocurrencyapiproject.service;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;

import java.util.List;

public interface CryptocoinService{

    public List<Cryptocoin> list();

    public void save(Cryptocoin cryptocoin);

    public void saveAll(List<Cryptocoin> cryptocoins);

    public Cryptocoin findByRank(int rank);

    public void deleteByRank(int rank);
}
