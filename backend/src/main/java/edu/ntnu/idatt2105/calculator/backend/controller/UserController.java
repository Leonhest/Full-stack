package edu.ntnu.idatt2105.calculator.backend.controller;

import edu.ntnu.idatt2105.calculator.backend.dto.UserData;
import edu.ntnu.idatt2105.calculator.backend.models.User;
import edu.ntnu.idatt2105.calculator.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.calculator.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.calculator.backend.services.AuthenticationService;
import edu.ntnu.idatt2105.calculator.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalcController.class);
    private final AuthenticationService authService;

      private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Object> register( @RequestBody UserData user) {
        LOGGER.info("User " + user.getUsername() + " is being registered!");
        return ResponseEntity.ok(authService.register(user));

    }


    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody AuthenticationRequest request) throws Exception {
        LOGGER.info("Trying to authenticate");
        return ResponseEntity.ok(authService.authenticate(request));

    }

}
