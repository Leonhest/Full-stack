package edu.ntnu.idatt2105.calculator.backend.dto;

import edu.ntnu.idatt2105.calculator.backend.models.Role;
import lombok.Data;

@Data
public class UserData {

    private String username;

    private String password;

}