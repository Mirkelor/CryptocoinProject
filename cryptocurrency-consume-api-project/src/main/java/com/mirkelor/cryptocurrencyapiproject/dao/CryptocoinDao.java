package com.mirkelor.cryptocurrencyapiproject.dao;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CryptocoinDao extends JpaRepository<Cryptocoin, Integer> {

    Cryptocoin findByRank(int rank);

    @Modifying
    @Transactional
    void deleteByRank(int rank);

}
