package ee.praktika.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.praktika.springdemo.entity.Customer;
import ee.praktika.springdemo.service.CustomerService;

@RestController
@RequestMapping( "/api" )
public class CustomerRestController {

    //autowire the CustomerService
    @Autowired
    private CustomerService customerService;

    //add mapping for GET / customers.
    @GetMapping( "/customers" )
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    //add mapping for GET / single-customer.
    @GetMapping( "/customers/{customerId}" )
    public Customer getCustomer( @PathVariable int customerId ){
        Customer theCustomer = customerService.getCustomer( customerId );

        return theCustomer;
    }
}
