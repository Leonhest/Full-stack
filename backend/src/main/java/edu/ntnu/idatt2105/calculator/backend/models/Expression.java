package edu.ntnu.idatt2105.calculator.backend.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "calculation")
public class Expression {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "expression_id", nullable = false)
    @NonNull
    private int expression_id;

    @Column(name = "expression", nullable = false)
    @NonNull
    private String expression;

    @Column(name = "result", nullable = false)
    @NonNull
    private double result;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username")
    @NonNull
    private User username;


    public Expression(String expression, double result, User username) throws NullPointerException {
        if (expression.isBlank()) throw new IllegalArgumentException("The expression cannot be empty.");
        this.expression = expression;
        this.result = result;
        this.username = username;
    }


}