package com.ridetogether.ridetogether.randomTests;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RandomTests {
    public static void main(String[] args) {

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Tsarigradsko+Shose+115%2C+Sofia%2C+Bulgaria&destination=Cherni+Vrah+Blvd+51%2C+Sofia%2C+Bulgaria&key=AIzaSyA291RPDrj_IEeUuu5cwADSXGLa5_KRHvE";
         RestTemplate restTemplate = new RestTemplate();
         String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

    @GetMapping("/test/url")
    public String test(){
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Tsarigradsko+Shose+115%2C+Sofia%2C+Bulgaria&destination=Cherni+Vrah+Blvd+51%2C+Sofia%2C+Bulgaria&key=AIzaSyA291RPDrj_IEeUuu5cwADSXGLa5_KRHvE";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
        return response;
    }
}
