package edu.ntnu.idatt2105.calculator.backend;

import edu.ntnu.idatt2105.calculator.backend.controller.UserController;
import edu.ntnu.idatt2105.calculator.backend.dto.UserData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        UserData user = new UserData();
        user.setUsername("Leon");
        user.setPassword("123");
    }

}
