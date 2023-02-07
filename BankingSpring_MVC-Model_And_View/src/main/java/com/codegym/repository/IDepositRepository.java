package com.codegym.repository;

import com.codegym.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IDepositRepository extends JpaRepository<Deposit, Integer> {
    @Query(value = "CALL sp_deposit(:customer_id, :money)", nativeQuery = true)
    String deposit(@Param("customer_id") Integer id, @Param("money") BigDecimal money);
}
