package com.agoracharis.customerservice.service.customer;


import com.agoracharis.customerservice.data.CustomerRequest;
import com.agoracharis.customerservice.data.CustomerResp;
import com.agoracharis.customerservice.data.UpdateCustomerReq;
import com.agoracharis.customerservice.exception.ResourceNotFoundException;
import com.agoracharis.customerservice.model.Address;
import com.agoracharis.customerservice.model.Customer;
import com.agoracharis.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public String saveCustomer(CustomerRequest customerRequest) {

        Customer customer = new Customer();
        customer.setEmail(customerRequest.email());
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setAddress(new Address(customerRequest.address().getState(),
                customerRequest.address().getCity(),
                customerRequest.address().getStreet(),
                customerRequest.address().getHouseNumber(),
                customerRequest.address().getZipCode()));
        customerRepository.save(customer);
        return "Customer created with ID "+customer.getId();
    }

    @Override
    @Transactional
    public String updateCustomerDetails(String customerId, UpdateCustomerReq updateCustomerReq) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setFirstName(updateCustomerReq.firstName());
        customer.setLastName(updateCustomerReq.lastName());

        Address newAddress = new Address(
                updateCustomerReq.address().getState(),
                updateCustomerReq.address().getCity(),
                updateCustomerReq.address().getStreet(),
                updateCustomerReq.address().getHouseNumber(),
                updateCustomerReq.address().getZipCode()
        );
        customer.setAddress(newAddress);
        customerRepository.save(customer);
        return "Customer details updated successfully";
    }

    @Override
    public List<CustomerResp> retrieveAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerResp(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        customer.getAddress()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean findCustomerById(String id){
       return customerRepository.existsById(id);
    }

    @Override
    public String deleteCustomerById(String id){
        customerRepository.deleteById(id);
         return "Customer deleted successfully";
    }

}
