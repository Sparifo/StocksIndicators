package com.upload.data.stocks.repository;

import com.upload.data.stocks.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {


}
