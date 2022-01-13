package com.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(value = PetClinicProperties.class)
@ServletComponentScan
public class PetclinicApplication{

    public static void main(String[] args) {
        SpringApplication.run(PetclinicApplication.class, args);
    }

}
