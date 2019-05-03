package com.mirkelor.cryptocurrencyapiproject.dao;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CryptocoinDao extends JpaRepository<Cryptocoin, Integer> {

    Cryptocoin findByRank(int rank);

    @Modifying
    @Transactional
    void deleteByRank(int rank);

    @Override
    Page<Cryptocoin> findAll(Pageable pageable);

    Page<Cryptocoin> findAllByNameOrSymbol(String search, Pageable pageable);
}
