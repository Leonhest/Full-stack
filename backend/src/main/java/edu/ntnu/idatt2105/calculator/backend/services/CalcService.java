package edu.ntnu.idatt2105.calculator.backend.services;



import edu.ntnu.idatt2105.calculator.backend.models.Expression;
import edu.ntnu.idatt2105.calculator.backend.models.User;
import edu.ntnu.idatt2105.calculator.backend.repository.ExpressionRepository;
import edu.ntnu.idatt2105.calculator.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CalcService {

    final ExpressionRepository expressionRepository;
    final UserRepository userRepository;

    public String calculate(ArrayList<String> exp){
        double ans = Double.parseDouble(exp.get(0));
        for(int i = 1; i<exp.size() ;i+=2){
            ans = exp.get(i).equals("+") ? ans+Double.parseDouble(exp.get(i+1))
                    : exp.get(i).equals("-") ? ans - Double.parseDouble(exp.get(i+1))
                    : exp.get(i).equals("x") ? ans * Double.parseDouble(exp.get(i+1))
                    : exp.get(i).equals("/") ? ans / Double.parseDouble(exp.get(i+1)): 0;
        }


        return  Double.toString(ans);
    }

    public boolean addCalculation(List<String> expression, double result, User username){
        StringBuilder stExp = new StringBuilder();
        for(String i: expression){
            stExp.append(i);
        }
        Expression exp = new Expression(stExp.toString(), result, username);
        expressionRepository.save(exp);
        return true;
    }

    public List<Expression> findAllExpressions(String username){

        List<Expression> expressions = new ArrayList<>();
        User user;
        try {
            user = userRepository.findByUsername(username).orElseThrow();
            expressions = expressionRepository.findByUsername(user);

        } catch (NoSuchElementException ignored){
        }
        return expressions;
    }
}
