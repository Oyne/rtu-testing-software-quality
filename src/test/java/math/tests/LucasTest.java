package math.tests;

import math.Calculator;
import math.data.LucasData;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class LucasTest {

    @Test(
            suiteName = "LucasSuite",
            priority = 1,
            groups = {"lucas", "valid"},
            dataProvider = "lucasValidData",
            dataProviderClass = LucasData.class
    )
    public void verifyLucasValid(int n, int expected) {
        assertThat(Calculator.lucas(n))
                .isEqualTo(expected);
    }

    @Test(
            suiteName = "LucasSuite",
            priority = 2,
            groups = {"lucas", "invalid"},
            dataProvider = "lucasInvalidData",
            dataProviderClass = LucasData.class
    )
    public void verifyLucasInvalid(int n, Class<? extends Throwable> exceptionClass, String message) {
        assertThatThrownBy(() -> Calculator.lucas(n))
                .isInstanceOf(exceptionClass)
                .hasMessage(message);
    }
}