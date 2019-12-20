package cz.danielson.sfgpetclinic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SfgPetClinicApplication {

    public static void main(String[] args) {
        log.info("Start application");
        SpringApplication.run(SfgPetClinicApplication.class, args);
    }

}
