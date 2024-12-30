package com.sainik.accounts.service.client;

import com.sainik.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="cards",fallback = CardsFallback.class)
public interface CardsFeignClient {
    @GetMapping(value="/api/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber);
}
