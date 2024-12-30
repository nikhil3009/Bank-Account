package com.sainik.accounts.service.impl;


import com.sainik.accounts.dto.AccountsDto;
import com.sainik.accounts.dto.CardsDto;
import com.sainik.accounts.dto.CustomerDetailsDto;
import com.sainik.accounts.dto.LoansDto;
import com.sainik.accounts.entity.Accounts;
import com.sainik.accounts.entity.Customer;
import com.sainik.accounts.exception.ResourceNotFoundException;
import com.sainik.accounts.mapper.AccountsMapper;
import com.sainik.accounts.mapper.CustomerMapper;
import com.sainik.accounts.repository.AccountsRepository;
import com.sainik.accounts.repository.CustomerRepository;
import com.sainik.accounts.service.ICustomersService;
import com.sainik.accounts.service.client.CardsFeignClient;
import com.sainik.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        if (null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

            ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);

        if (null != cardsDtoResponseEntity) {
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
    }

        return customerDetailsDto;

    }
}