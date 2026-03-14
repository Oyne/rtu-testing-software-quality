package tests;

import math.Calculator;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class LucasTest {

    @Test(
            dataProvider = "lucasValidData",
            dataProviderClass = data.LucasData.class,
            priority = 1,
            groups = {"lucas", "valid"}
    )
    public void verifyLucasValid(int n, int expected) {
        assertThat(Calculator.lucas(n))
                .isEqualTo(expected);
    }

    @Test(
            dataProvider = "lucasInvalidData",
            dataProviderClass = data.LucasData.class,
            priority = 2,
            groups = {"lucas", "invalid"}
    )
    public void verifyLucasInvalid(int n, Class<? extends Throwable> exceptionClass, String message) {
        assertThatThrownBy(() -> Calculator.lucas(n))
                .isInstanceOf(exceptionClass)
                .hasMessage(message);
    }
}