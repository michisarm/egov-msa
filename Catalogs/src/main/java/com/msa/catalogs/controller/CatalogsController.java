package com.msa.catalogs.controller;

import com.msa.catalogs.service.CustomerApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs/customerinfo")
public class CatalogsController {

    @Autowired
    private CustomerApiService customerApiService;

    @GetMapping(path = "/{customerId}")
    public String getCustomerInfo(@PathVariable String customerId) {
        String customerInfo = customerApiService.getCustomerDetail(customerId);
        // throw new RuntimeException("I/O Exception"); // RuntimeException 은 예제 실행시 동작안함
        // System.out.println("response customerInfo : " + customerInfo);
        return String.format("[Customer id = %s at %s %s ]", customerId, System.currentTimeMillis(), customerInfo);
    }

}