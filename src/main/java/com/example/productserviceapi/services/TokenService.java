package com.example.productserviceapi.services;


import com.example.productserviceapi.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    private RestTemplate restTemplate;

    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String token) {
        try {
            UserDto userDto = restTemplate.postForObject("http://localhost:9090/users/validate/" + token, null, UserDto.class);
            return userDto != null;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}