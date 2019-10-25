package ee.praktika.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        if( theCustomer == null ) {
            throw new CustomerNotFoundException( "Customer id not found: " + customerId );
        }

        return theCustomer;
    }

    //add mapping for POST / customer.
    @PostMapping( "/customers" )
    public Customer addCustomer( @RequestBody Customer theCustomer ){

        //also just in case the data is passed in JSON, need to set the ID to 0 to force a new item save over updating.
        theCustomer.setId( 0 );

        customerService.saveCustomer( theCustomer );

        return theCustomer;
    }
}
