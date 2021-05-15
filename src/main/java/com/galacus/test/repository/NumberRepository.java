package com.galacus.test.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galacus.test.dao.NumbersDao;

public interface NumberRepository extends JpaRepository<NumbersDao, Long>{

}
