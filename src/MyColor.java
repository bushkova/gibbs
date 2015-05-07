import java.math.BigInteger;

/**
 * Created by anbu0414 on 5/5/2015.
 */
public class MyColor {
    private int red, green, blue;
    private String name;

    public MyColor(int r, int g, int b, String n) {
        red = r;
        green = g;
        blue = b;
        name = n;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexa() {
        return Integer.toHexString(Integer.valueOf(String.valueOf(getBigIntFromRGB(red,green,blue))));
    }

    public static BigInteger getBigIntFromRGB(int r, int g, int b) {
        String s = String.valueOf((r*65536)+(g*256)+b);
        return new BigInteger(s);
    }

}
