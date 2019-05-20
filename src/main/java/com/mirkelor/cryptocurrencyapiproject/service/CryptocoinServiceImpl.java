package com.mirkelor.cryptocurrencyapiproject.service;

import com.mirkelor.cryptocurrencyapiproject.dao.CryptocoinDao;
import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptocoinServiceImpl implements CryptocoinService{

    private CryptocoinDao cryptocoinDao;

    public CryptocoinServiceImpl(CryptocoinDao cryptocoinDao) {
        this.cryptocoinDao = cryptocoinDao;
    }

    @Override
    public List<Cryptocoin> list(){
        return cryptocoinDao.findAll();
    }

    @Override
    public void save(Cryptocoin cryptocoin){

        cryptocoinDao.save(cryptocoin);
    }

    @Override
    public void saveAll(List<Cryptocoin> cryptocoins){

        cryptocoinDao.saveAll(cryptocoins);
    }

    @Override
    public Cryptocoin findByRank(int rank) {
        return cryptocoinDao.findByRank(rank);
    }

    @Override
    public void deleteByRank(int rank) {
        cryptocoinDao.deleteByRank(rank);
    }

    @Override
    public List<Cryptocoin> findAll() {
        return cryptocoinDao.findAll();
    }

    @Override
    public Page<Cryptocoin> findAll(Pageable pageable) {
        return cryptocoinDao.findAll(pageable);
    }

    @Override
    public Page<Cryptocoin> findBySearch(String search, Pageable pageable) {
        return cryptocoinDao.findBySearch(search, pageable);
    }
}
