package data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class CalculatorData {
    public static Stream<Arguments> additionData() {
        return Stream.of(
                Arguments.of(0, 5, 5),
                Arguments.of(5, 0, 5),
                Arguments.of(-5, 0, -5),
                Arguments.of(0, -5, -5),
                Arguments.of(5, 5, 10),
                Arguments.of(-5, 5, 0),
                Arguments.of(5, -5, 0),
                Arguments.of(-5, -5, -10));
    }

    public static Stream<Arguments> subtractionData() {
        return Stream.of(
                Arguments.of(0, 5, -5),
                Arguments.of(0, -5, 5),
                Arguments.of(5, 0, 5),
                Arguments.of(-5, 0, -5),
                Arguments.of(5, 5, 0),
                Arguments.of(-5, 5, -10),
                Arguments.of(5, -5, 10),
                Arguments.of(-5, -5, 0));
    }

    public static Stream<Arguments> multiplicationData() {
        return Stream.of(
                Arguments.of(0, 5, 0),
                Arguments.of(0, -5, 0),
                Arguments.of(10, 2, 20),
                Arguments.of(-10, 2, -20),
                Arguments.of(10, -2, -20),
                Arguments.of(-10, -2, 20));
    }

    public static Stream<Arguments> divisionValidData() {
        return Stream.of(
                Arguments.of(0, 5, 0),
                Arguments.of(0, -5, 0),
                Arguments.of(10, 2, 5),
                Arguments.of(10, 10, 1),
                Arguments.of(-10, 10, -1),
                Arguments.of(10, -10, -1),
                Arguments.of(-10, -10, 1));
    }

    public static Stream<Arguments> divisionInvalidData() {
        return Stream.of(
                Arguments.of(5, 0, ArithmeticException.class, "Cannot divide by zero"),
                Arguments.of(0, 0, ArithmeticException.class, "Cannot divide by zero"));
    }

    public static Stream<Arguments> fibonacciValidData() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(5, 5),
                Arguments.of(20, 6765),
                Arguments.of(40, 102334155));
    }

    public static Stream<Arguments> fibonacciInvalidData() {
        return Stream.of(
                Arguments.of(-1, IllegalArgumentException.class, "n must be non-negative"));
    }
}
