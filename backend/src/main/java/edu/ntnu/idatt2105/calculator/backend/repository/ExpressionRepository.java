package edu.ntnu.idatt2105.calculator.backend.repository;


import edu.ntnu.idatt2105.calculator.backend.models.Expression;
import edu.ntnu.idatt2105.calculator.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Integer>{
    List<Expression> findByUsername(User username);
}