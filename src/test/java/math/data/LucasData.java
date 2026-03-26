package math.data;

import org.testng.annotations.DataProvider;

public class LucasData {
    @DataProvider(name = "lucasValidData")
    public static Object[][] lucasValidData() {
        return new Object[][]{
                {0, 2},
                {1, 1},
                {2, 3},
                {3, 4},
                {5, 11},
                {20, 15127},
                {40, 228826127}
        };
    }

    @DataProvider(name = "lucasInvalidData")
    public static Object[][] lucasInvalidData() {
        return new Object[][]{
                {-1, IllegalArgumentException.class, "n must be non-negative"}
        };
    }

}
