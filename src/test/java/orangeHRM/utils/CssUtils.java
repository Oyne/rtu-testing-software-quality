package orangeHRM.utils;

public class CssUtils {
    public static String normalizeColor(String cssValue) {
        if (cssValue == null || cssValue.isEmpty()) return "";
        if (cssValue.startsWith("rgba") && cssValue.endsWith(", 1)")) {
            cssValue = cssValue.replace("rgba", "rgb").replace(", 1)", ")");
        }
        return cssValue.trim();
    }
}
