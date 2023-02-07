package com.codegym.model;


import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customers")
@Component
public class Customer extends BaseEntity implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name", nullable = false)
    private String name;

    @NotBlank
    @Column(precision = 12, nullable = false, updatable = false)
    private BigDecimal balance;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;

    @NotBlank
    private String phone;

    public Customer() {
    }

    public Customer(Integer id, String name, BigDecimal balance, String email, String address, String phone,
                    Date createdAt, Date updatedAt, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Customer(Integer id, String name, String email, String address, String phone, Date createdAt, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        String name = customer.getName();
        String phone = customer.getPhone();
        String email = customer.getEmail();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null");
        } else if (name.length() < 2 || name.length() > 50) {
            errors.rejectValue("name", "name.length");
        }

        if (phone.length() > 11 || phone.length() < 10) {
            errors.rejectValue("number", "number.length");
        } else if (!phone.startsWith("0")) {
            errors.rejectValue("number", "number.startsWith");
        } else if (!phone.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("number", "number.matches");
        }

        if (email.length() == 0) {
            errors.rejectValue("email", "email.null");
        } else if (!email.matches("^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
            errors.rejectValue("email", "email.matches");
        }
    }
}
