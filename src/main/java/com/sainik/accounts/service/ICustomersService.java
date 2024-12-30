package com.sainik.accounts.service;

import com.sainik.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
