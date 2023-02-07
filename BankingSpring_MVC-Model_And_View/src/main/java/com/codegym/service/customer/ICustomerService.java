package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.IGeneral;

import java.math.BigDecimal;
import java.util.List;


public interface ICustomerService extends IGeneral<Customer> {
    List<Customer> findAllByDeletedIsFalseAndIdNot(Integer id);
    List<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByIdNot(int id);
    void deposit(Customer customer, Deposit deposit);
    void withdraw(Customer customer, Withdraw withdraw);
    void transfer(Transfer transfer);

}
