package com.agoracharis.customerservice.service.customer;

import com.agoracharis.customerservice.data.CustomerRequest;
import com.agoracharis.customerservice.data.CustomerResp;
import com.agoracharis.customerservice.data.UpdateCustomerReq;

import java.util.List;

public interface CustomerService {

    String saveCustomer(CustomerRequest customerRequest);

    String updateCustomerDetails(String customerId, UpdateCustomerReq updateCustomerReq);

    List<CustomerResp> retrieveAllCustomers();

    Boolean findCustomerById(String id);

    String deleteCustomerById(String id);
}
