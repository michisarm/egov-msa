package com.msa.catalogs.serviceImpl;

import com.msa.catalogs.service.CustomerApiService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerApiServiceImpl implements CustomerApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    /**
     * 강제 fallback을 위해 1ms 응답 조건을 추가
     */
    @HystrixCommand(
//            commandProperties = {
//                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1")
//            },
            fallbackMethod = "getCustomerDetailFallback"
    )
    public String getCustomerDetail(String customerId) {
//        return customerId;
//        return restTemplate.getForObject("http://localhost:8082/customers/" + customerId, String.class);
        // ribbon load balancer test
        return restTemplate.getForObject("http://customer/customers/" + customerId, String.class);
    }

    public String getCustomerDetailFallback(String customerId, Throwable ex) {
        System.out.println("Error:" + ex.getMessage());
        return "고객정보 조회가 지연되고 있습니다.";
    }

}
