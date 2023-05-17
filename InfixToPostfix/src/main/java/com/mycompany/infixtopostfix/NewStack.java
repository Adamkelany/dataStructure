/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infixtopostfix;

/**
 *
 * @author Adam Kelany
 */
public class NewStack {
    private char[] arr;
    private int top;
    private int size;

    public NewStack(int size) {
        this.arr = new char[size];
        this.top = -1;
        this.size = size;
    }

    public void push(char value) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push element");
            return;
        }
        arr[++top] = value;
    }

    public char pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop element");
            return '\0';
        }
        return arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public char peek() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return '\0';
        }
        return arr[top];
    }
}
