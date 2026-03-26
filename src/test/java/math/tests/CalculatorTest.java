package math.tests;

import math.Calculator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    @Order(1)
    @Tag("addition")
    @ParameterizedTest(name = "{0} + {1} -> {2}")
    @MethodSource("math.data.CalculatorData#additionData")
    public void verifyAddition(int a, int b, int expected) {
        assertThat(Calculator.add(a, b)).isEqualTo(expected);
    }

    @Order(2)
    @Tag("subtraction")
    @ParameterizedTest(name = "{0} - {1} -> {2}")
    @MethodSource("math.data.CalculatorData#subtractionData")
    public void verifySubtraction(int a, int b, int expected) {
        assertThat(Calculator.subtract(a, b)).isEqualTo(expected);
    }

    @Order(3)
    @Tag("multiplication")
    @ParameterizedTest(name = "{0} * {1} -> {2}")
    @MethodSource("math.data.CalculatorData#multiplicationData")
    public void verifyMultiplication(int a, int b, int expected) {
        assertThat(Calculator.multiply(a, b)).isEqualTo(expected);
    }

    @Order(4)
    @Tag("division")
    @Tag("valid")
    @ParameterizedTest(name = "{0} / {1} -> {2}")
    @MethodSource("math.data.CalculatorData#divisionValidData")
    public void verifyDivisionValid(int a, int b, Object expected) {
        assertThat(Calculator.divide(a, b))
                .isEqualTo(expected);
    }

    @Order(5)
    @Tag("division")
    @Tag("invalid")
    @ParameterizedTest(name = "{0} / {1} -> throws {2}, with message: {3}")
    @MethodSource("math.data.CalculatorData#divisionInvalidData")
    public void verifyDivisionInvalid(int a, int b, Class<? extends Throwable> exceptionClass, String message) {
        assertThatThrownBy(() -> Calculator.divide(a, b))
                .isInstanceOf(exceptionClass)
                .hasMessage(message);

    }

    @Order(6)
    @Tag("fibonacci")
    @Tag("valid")
    @ParameterizedTest(name = "Fibonacci({0}) -> {1}")
    @MethodSource("math.data.CalculatorData#fibonacciValidData")
    void verifyFibonacci(int n, int expected) {

        assertThat(Calculator.fibonacci(n)).isEqualTo(expected);
    }

    @Order(7)
    @Tag("fibonacci")
    @Tag("invalid")
    @ParameterizedTest(name = "Fibonacci({0}) -> throws {1}, with message: {2}")
    @MethodSource("math.data.CalculatorData#fibonacciInvalidData")
    void verifyFibonacciInvalid(int n, Class<? extends Throwable> exceptionClass, String message) {

        assertThatThrownBy(() -> Calculator.fibonacci(n))
                .isInstanceOf(exceptionClass)
                .hasMessage(message);
    }
}
