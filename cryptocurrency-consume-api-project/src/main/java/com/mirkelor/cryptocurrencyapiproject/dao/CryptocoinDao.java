package com.mirkelor.cryptocurrencyapiproject.dao;

import com.mirkelor.cryptocurrencyapiproject.entity.Cryptocoin;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;

public interface CryptocoinDao extends JpaRepository<Cryptocoin, Integer> {

    Cryptocoin findByRank(int rank);

    @Modifying
    @Transactional
    void deleteByRank(int rank);

    @Query("Select c from Cryptocoin c where c.name like %?1% or c.symbol like %?1%")
    Page<Cryptocoin> findBySearch(String search, Pageable pageable);
}
