package dokerplp.controller.res;

public class AreaCheckUtil {
    public static float left(float x){
        float hor = x * 90 + 500f;
        return hor / 1000;
    }

    public static float top(float y){
        float ver = 500f - y * 90;
        return ver / 1000;
    }

    public static boolean isIn(float x, float y, float r) {
        return (isCircle(x, y, r) || isRect(x, y, r) || isTriangle(x, y, r));
    }

    private static boolean isTriangle(float x, float y, float r) {
        return (x <= 0 && y <= 0 && y + 0.5*x + r / 2 >= 0);
    }

    private static boolean isCircle(float x, float y, float r) {
        return (x >= 0 && y >= 0 && x * x + y * y <= r * r);
    }

    private static boolean isRect(float x, float y, float r) {
        return (x >= 0 && y <= 0 && x <= r && y >= -1 * r / 2);
    }
}