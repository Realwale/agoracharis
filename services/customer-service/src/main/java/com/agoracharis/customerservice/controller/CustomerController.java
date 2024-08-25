package com.agoracharis.customerservice.controller;


import com.agoracharis.customerservice.data.CustomerRequest;
import com.agoracharis.customerservice.data.CustomerResp;
import com.agoracharis.customerservice.data.UpdateCustomerReq;
import com.agoracharis.customerservice.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.saveCustomer(customerRequest));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomerDetail(@PathVariable String customerId, @RequestBody @Valid UpdateCustomerReq customerRequest){
        return ResponseEntity.ok(customerService.updateCustomerDetails(customerId, customerRequest));
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> customerExistsById(@PathVariable String customerId){
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResp>> retrieveAllCustomerRecords(){
        return ResponseEntity.ok(customerService.retrieveAllCustomers());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomerRecord(@PathVariable String customerId){
        return ResponseEntity.ok(customerService.deleteCustomerById(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResp> findCustomerById(@PathVariable String customerId){
        return ResponseEntity.ok(customerService.retrieveCustomerById(customerId));
    }
}
