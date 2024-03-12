package org.example.math;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class AdditionTest {

    private Addition addition;

    //given
    @BeforeEach
    void setUp(){

        addition = new Addition();
    }

    @Test
    void givenTwoPositiveValuesShouldReturnValueSum(){
        //when
        int result = addition.add(2,3);

        //then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void givenTwoOppositeValuesShouldReturnZero(){
        //when
        int result = addition.add(-2,2);

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void givenPositiveAndNegativeValueShouldReturnSubstraction(){
        //when
        int result = addition.add(-2,1);

        //then
        assertThat(result).isEqualTo(-1);
    }

}