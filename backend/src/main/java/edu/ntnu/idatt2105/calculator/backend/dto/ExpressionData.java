package edu.ntnu.idatt2105.calculator.backend.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ExpressionData {
    private ArrayList<String> expression;
    private String username;
}