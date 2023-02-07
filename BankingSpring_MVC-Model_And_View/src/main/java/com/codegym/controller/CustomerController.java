package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.dto.EditCustomerDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"", "/customer"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    private List<Customer> customerList;
    private ModelAndView modelAndView;
    private Customer customer;
    private Optional<Customer> customerOptional;

    @GetMapping("/edit/{id}")
    private ModelAndView showEdit(@PathVariable Integer id) {
        modelAndView = new ModelAndView("/customer/edit");
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());

        } else {
            EditCustomerDTO editCustomerDTO = new EditCustomerDTO();
            customer = customerOptional.get();

            editCustomerDTO.setId(customer.getId());
            editCustomerDTO.setName(customer.getName());
            editCustomerDTO.setEmail(customer.getEmail());
            editCustomerDTO.setPhone(customer.getPhone());

            if (customer.getDeleted()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "This customer is suspended!");
            }

            modelAndView.addObject("customer", customer);
            modelAndView.addObject("editCustomerDTO", editCustomerDTO);
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    private ModelAndView showDelete(@PathVariable Integer id) {
        modelAndView = new ModelAndView("/customer/delete");
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());
        } else {
            customer = customerOptional.get();

            if (customer.getDeleted()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "This customer has been suspended");
            }

            modelAndView.addObject("customer", customer);
        }
        return modelAndView;
    }


    @GetMapping("/create")
    private ModelAndView create() {
        modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/")
    private ModelAndView index() {
        customerList = customerService.findAllByDeletedIsFalse();
        modelAndView = new ModelAndView("/customer/index");
        modelAndView.addObject("customerList", customerList);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    private ModelAndView edit(@PathVariable Integer id, @Valid EditCustomerDTO editCustomerDTO, BindingResult br) {
        modelAndView = new ModelAndView("/customer/edit");
        customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());
        } else {
            new EditCustomerDTO().validate(editCustomerDTO, br);
            customer = customerOptional.get();

            if (br.hasFieldErrors()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("editCustomerDTO", editCustomerDTO);

                return modelAndView;
            }

            customer.setName(editCustomerDTO.getName());
            customer.setEmail(editCustomerDTO.getEmail());
            customer.setPhone(editCustomerDTO.getPhone());
            customer.setUpdatedAt(new Date());

            customerService.save(customer);

            modelAndView.addObject("message", "Edit success!");
            modelAndView.addObject("error", false);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("editCustomerDTO", editCustomerDTO);
        }

        return modelAndView;

//        customerOptional = customerService.findById(id);
//        if(customerOptional.isPresent()){
//            System.out.println("post edit");
//            modelAndView = new ModelAndView("redirect:/");
//            customer.setId(id);
//            customer.setCreatedAt(customerOptional.get().getCreatedAt());
//            customer.setUpdatedAt(DateUtils.dateToString(new Date()));
//            customerService.save(customer);
//            return modelAndView;
//        }else{
//            modelAndView = new ModelAndView("redirect:/");
//            return modelAndView;
//        }
    }

    @PostMapping("/delete/{id}")
    private ModelAndView delete(@PathVariable Integer id) {
        modelAndView = new ModelAndView("/customer/delete");
        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Customer ID invalid");
            modelAndView.addObject("customer", new Customer());
        } else {
            customer = customerOptional.get();
            customer.setDeleted(true);
            customerService.save(customer);

            modelAndView.addObject("error", false);
            modelAndView.addObject("customer", customer);
        }

        return modelAndView;
    }

    @PostMapping("/create")
    private ModelAndView save(Customer customer) {
        customer.setCreatedAt(new Date());
        customer.setBalance(BigDecimal.ZERO);
        customerService.save(customer);
        return new ModelAndView("redirect:/");
    }
}
