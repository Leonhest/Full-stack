package edu.ntnu.idatt2105.calculator.backend.controller;


import edu.ntnu.idatt2105.calculator.backend.dto.ExpressionData;
import edu.ntnu.idatt2105.calculator.backend.dto.UserData;
import edu.ntnu.idatt2105.calculator.backend.models.Expression;
import edu.ntnu.idatt2105.calculator.backend.models.User;
import edu.ntnu.idatt2105.calculator.backend.repository.UserRepository;
import edu.ntnu.idatt2105.calculator.backend.services.CalcService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CalcController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcController.class);
    final CalcService calcService;
    final UserRepository userRepository;


    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> calculate(@RequestBody ExpressionData body) {
        LOGGER.info("Received: " + body.getExpression());
        String result;
        try {
            result = calcService.calculate(body.getExpression());
            calcService.addCalculation(body.getExpression(), Double.parseDouble(result), userRepository.findByUsername(body.getUsername()).orElseThrow());
        } catch (NumberFormatException e) {
            LOGGER.error("Error with expression format: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Received error from calculation model " + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        LOGGER.info("Sending: " + result);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/calculate/log")
    public ResponseEntity<List<Expression>> getLog(@RequestBody UserData username) {
        return ResponseEntity.ok(calcService.findAllExpressions(username.getUsername()));
    }

}
