package healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @Test
    void shouldReturnTrueWhenDietRecommended() {

        // given
        double weight = 89.0;
        double height = 1.72;

        // when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        // then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalseWhenDietNotRecommended() {

        // given
        double weight = 50.0;
        double height = 1.92;

        // when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        // then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowArithmeticExceptionWhenHeightZero() throws NullPointerException {

        // given
        double weight = 50.0;
        double height = 0.0;

        // when
        Executable executable = () -> BMICalculator.isDietRecommended(weight,height);

        // then
        assertThrows(ArithmeticException.class,executable);
    }

    @Test
    void shouldReturnCoderWithWorstBMIWhenCoderListNotEmpty() {
        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.89, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));

        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        // then
        assertAll(
                () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                () -> assertEquals(98.0, coderWorstBMI.getWeight())
        );

    }

    @Test
    void shouldReturnNullWithWorstBMIWhenCoderListEmpty() {
            // given
            List<Coder> coders1 = new ArrayList<>();

            // when
            Coder coderWorstBMI1 = BMICalculator.findCoderWithWorstBMI(coders1);

            // then
           assertNull(coderWorstBMI1);
    }

    @Test
    void shouldReturnCorrectBMIScoreArrayWhenCoderListIsNotEmpty() {
        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.89, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {16.8,29.59,19.53};

        // when
        double[] bmiScores = BMICalculator.getBMIScores(coders);
        System.out.println("hello");
        for (double b : bmiScores) {
            System.out.println(b);
        }

        // then
        assertArrayEquals(expected,bmiScores);
    }

    @Test
    void shouldThrowArithmeticExceptionWhenHeightZeroCalculateBMI() {
        // given
        double height = 0.0;
        double weight = 40.45;
        // when
        Executable executable = () -> BMICalculator.calculateBMI(new Coder(height,weight));
        // then
        assertThrows(ArithmeticException.class,executable);

    }

    @Test
    void shouldReturnBMIWhenHeightNotZercoCalculateBMI() {
        // given
        double height = 1.89;
        double weight = 100.45;
        double expected = 28.12;
        // when
        double actual =  BMICalculator.calculateBMI(new Coder(height,weight));
        // then
        System.out.println(actual);
        assertEquals(expected,actual);

    }



}