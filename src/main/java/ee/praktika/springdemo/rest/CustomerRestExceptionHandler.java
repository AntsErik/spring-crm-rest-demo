package ee.praktika.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    //add and exception handler for CustomerNotFoundException
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException( CustomerNotFoundException exc ){

        //create the Customer Error Response
        CustomerErrorResponse error = new CustomerErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            exc.getMessage(),
            System.currentTimeMillis() );

        //return the Response Entity and pass that in the response with the appropriate status code.
        return new ResponseEntity<>( error, HttpStatus.NOT_FOUND );
    }

    //add another exception handler to catch all exception (whatever the exception).
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException( Exception exc ){

        //create the Custom BAD-REQUEST Error Response
        CustomerErrorResponse error = new CustomerErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            exc.getMessage(),
            System.currentTimeMillis() );

        //return the Response Entity and pass that in the response with the appropriate status code.
        return new ResponseEntity<>( error, HttpStatus.BAD_REQUEST );
    }

}
