package edu.ntnu.idatt2105.calculator.backend.services;


import edu.ntnu.idatt2105.calculator.backend.dto.UserData;
import edu.ntnu.idatt2105.calculator.backend.models.Role;
import edu.ntnu.idatt2105.calculator.backend.models.User;
import edu.ntnu.idatt2105.calculator.backend.repository.UserRepository;
import edu.ntnu.idatt2105.calculator.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.calculator.backend.security.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 This service class handles the business logic for authentication-related operations.
 It implements the IAuthenticationService interface.

 */
@RequiredArgsConstructor
@Service
public class AuthenticationService{


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);



    /**
     * Authenticates a user with the given credentials.
     *
     * @param request the authentication request containing the username and password of the user.
     * @return an AuthenticationResponse containing the JWT token of the authenticated user.
     * @throws UsernameNotFoundException if the username of the user is not found in the database.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Registers a user to the system.
     *
     * @param userData the information of the user to be registered.
     * @return an AuthenticationResponse containing the JWT token of the user.
     */
    @Transactional
    public AuthenticationResponse register(UserData userData) {
        User user = User
                .builder()
                .username(userData.getUsername())
                .password(passwordEncoder.encode(userData.getPassword()))
                .role(Role.ADMIN)
                .build();
        if (userRepository.findByUsername(userData.getUsername()).isPresent())
            throw new IllegalArgumentException("Username already exists");
        userRepository.save(user);

        logger.info(String.format("User %s has been saved in the DB!", user.getUsername()));

        String jwtToken = jwtService.generateToken(user);
        logger.info("Their JWT is: " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}