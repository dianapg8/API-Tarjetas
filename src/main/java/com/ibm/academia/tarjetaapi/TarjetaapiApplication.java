package com.ibm.academia.tarjetaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class TarjetaapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TarjetaapiApplication.class, args);
    }

}
