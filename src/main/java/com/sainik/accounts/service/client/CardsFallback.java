package com.sainik.accounts.service.client;

import com.sainik.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
        return null;
    }

}
