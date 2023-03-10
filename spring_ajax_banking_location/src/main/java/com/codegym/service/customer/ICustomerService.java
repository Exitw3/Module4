package com.codegym.service.customer;

import com.codegym.model.*;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    List<CustomerDTO> getAllCustomerDTO();

//    Customer save(Customer customer, LocationRegion locationRegion);

    void softDelete(long customerId);

    List<Customer> findAllByIdNot(long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Optional<Customer> findByPhoneAndIdIsNot(String phone, Long id);

    Optional<CustomerDTO> getByEmailDTO(String email);

    Optional<CustomerDTO> getByPhoneDTO(String phone);

    Customer deposit(Customer customer, Deposit deposit);

    Customer withdraw(Customer customer, Withdraw withdraw);

    Customer transfer(Transfer transfer);

}
