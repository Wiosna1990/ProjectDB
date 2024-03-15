package org.example.math;

public class Division {

    public double divide(double a, double b){

        if (b == 0){
            throw new ArithmeticException("Never divide by zero!");
        }
        return a/b;
    }

}
