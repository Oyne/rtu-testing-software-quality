package tests;

import math.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {

    @ParameterizedTest(name = "{0} + {1} -> {2}")
    @MethodSource("data.CalculatorData#additionData")
    public void verifyAddition(int a, int b, int expected) {
        assertThat(Calculator.add(a, b))
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} - {1} -> {2}")
    @MethodSource("data.CalculatorData#subtractionData")
    public void verifySubtraction(int a, int b, int expected) {
        assertThat(Calculator.subtract(a, b))
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} * {1} -> {2}")
    @MethodSource("data.CalculatorData#multiplicationData")
    public void verifyMultiplication(int a, int b, int expected) {
        assertThat(Calculator.multiply(a, b))
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} / {1} -> {2}")
    @MethodSource("data.CalculatorData#divisionData")
    public void verifyDivisionByZero(int a, int b, Object expected) {
        if (b != 0) {
            assertThat(Calculator.divide(a, b))
                    .isEqualTo(expected);
        } else {
            assertThatThrownBy(() -> Calculator.divide(a, b))
                    .isInstanceOf(ArithmeticException.class)
                    .hasMessage(expected.toString());
        }
    }

    @ParameterizedTest(name = "Fibonacci({0}) -> {1}")
    @MethodSource("data.CalculatorData#fibonacciData")
    void verifyFibonacci(int n, Object expected) {

        if (expected instanceof Integer expectedValue) {
            assertThat(Calculator.fibonacci(n))
                    .isEqualTo(expectedValue);
        } else {
            assertThatThrownBy(() -> Calculator.fibonacci(n))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(expected.toString());
        }
    }
}
