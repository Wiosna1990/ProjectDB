package org.example.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    private Division division;

    //given
    @BeforeEach
    void setUp() {
        division = new Division();
    }

    @Test
    void givenNegativeAndPositiveValueShouldReturnNegative() {
        //when
        double result = division.divide(-1.0, 3.0);

        //then
        assertThat(result).isEqualTo(-1.0 / 3.0);
    }

    @Test
    void givenTwoNegativeValueShouldReturnPositive() {
        //when
        double result = division.divide(-3.0, -3.0);

        //then
        assertThat(result).isPositive();
    }

    @Test
    void givenFirstZeroAndSecondPositiveShouldReturnZero() {
        //when
        double result = division.divide(0, 5.0);

        //then
        assertThat(result).isZero();
    }

    @Test
    void givenFirstPositiveAndSecondZeroShouldReturnException() {
        //when
        Throwable thrown = catchThrowable(() -> division.divide(2.0, 0));

        //then
        assertThat(thrown).isInstanceOf(ArithmeticException.class);

    }
}