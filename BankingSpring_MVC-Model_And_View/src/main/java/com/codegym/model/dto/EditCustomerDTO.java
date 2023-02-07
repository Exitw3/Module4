package com.codegym.model.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EditCustomerDTO implements Validator {

    private Integer id;
    private String name;
    private String phone;
    private String email;

    public EditCustomerDTO() {
    }

    public EditCustomerDTO(Integer id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EditCustomerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditCustomerDTO editCustomerDTO = (EditCustomerDTO) target;
        String name = editCustomerDTO.getName();
        String phone = editCustomerDTO.getPhone();
        String email = editCustomerDTO.getEmail();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");

        if (name.length() == 0) {
            errors.rejectValue("name", "name.null");
        } else if (name.length() < 2 || name.length() > 50) {
            errors.rejectValue("name", "name.length");
        }

        if (phone.length() > 11 || phone.length() < 10) {
            errors.rejectValue("phone", "phone.length");
        } else if (!phone.startsWith("0")) {
            errors.rejectValue("phone", "phone.startsWith");
        } else if (!phone.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("phone", "phone.matches");
        }

        if (email.length() == 0) {
            errors.rejectValue("email", "email.null");
        } else if (!email.matches("^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
            errors.rejectValue("email", "email.matches");
        }
    }
}
