/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.infixtopostfix;

/**
 *
 * @author Adam Kelany
 */

import java.util.Scanner;

public class InfixToPostfix {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String infixExpression = scanner.nextLine();
        
        String postfixExpression = convertToPostfix(infixExpression);
        System.out.println("Postfix expression: " + postfixExpression);
        
        int result = evaluatePostfix(postfixExpression);
        System.out.println("Result: " + result);
    }
    
    public static String convertToPostfix(String infixExpression) {
        NewStack stack = new NewStack(infixExpression.length());
        StringBuilder postfixExpression = new StringBuilder();
        
        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);
            
            if (Character.isDigit(c)) {
                postfixExpression.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfixExpression.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    throw new IllegalArgumentException("Invalid expression");
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfixExpression.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        while (!stack.isEmpty()) {
            postfixExpression.append(stack.pop());
        }
        
        return postfixExpression.toString();
    }
    
    public static int evaluatePostfix(String postfixExpression) {
        NewStack stack = new NewStack(postfixExpression.length());
        
        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);
            
            if (Character.isDigit(c)) {
                stack.push((char) (c - '0'));
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                
                switch (c) {
                    case '+':
                        stack.push((char) (operand1 + operand2));
                        break;
                    case '-':
                        stack.push((char) (operand1 - operand2));
                        break;
                    case '*':
                        stack.push((char) (operand1 * operand2));
                        break;
                    case '/':
                        stack.push((char) (operand1 / operand2));
                        break;
                }
            }
        }
        
        return stack.pop();
    }
    
    public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
