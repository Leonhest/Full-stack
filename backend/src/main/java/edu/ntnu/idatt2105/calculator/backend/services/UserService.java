package edu.ntnu.idatt2105.calculator.backend.services;

import edu.ntnu.idatt2105.calculator.backend.dto.UserData;
import edu.ntnu.idatt2105.calculator.backend.models.Role;
import edu.ntnu.idatt2105.calculator.backend.models.User;
import edu.ntnu.idatt2105.calculator.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean registerUser(UserData userData){
        User user = User
                .builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        return true;
    }
}
