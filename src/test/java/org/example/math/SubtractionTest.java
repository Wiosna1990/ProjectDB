package org.example.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest {

    private Subtraction subtraction;

    //given
    @BeforeEach
    void setUp(){
        subtraction = new Subtraction();
    }

    @Test
    void givenFirstPositiveAndSecondNegativeValueShouldReturnPositive(){
        //when
        int result = subtraction.subtract(5,-5);

        //then
        assertThat(result).isEqualTo(10);
    }

    @Test
    void givenFirstNegativeAndSecondPositiveValueShouldReturnNegative(){
        //when
        int result = subtraction.subtract(-2, 5);

        //then
        assertThat(result).isEqualTo(-7);
    }

    @Test
    void givenZeroAndNegativeValueShouldReturnPositive(){
        //when
        int result = subtraction.subtract(0,-2);

        //then
        assertThat(result).isEqualTo(2);
    }


}