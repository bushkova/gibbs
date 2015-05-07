import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created by anbu0414 on 5/5/2015.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("C:\\Work\\Study\\Gibbs_fields\\nature.jpg"));
        /*Color color = new Color(176,23,31);
        int rgb = color.getRGB();
        System.out.println("color.getRGB() = " + rgb);
        System.out.println("color.getBlue() = " + color.getBlue());
        System.out.println("color.getRed() = " + color.getRed());
        System.out.println("color.getGreen() = " + color.getGreen());

        BigInteger bigInt = getBigIntFromRGB(176,23,31);
        String bigIntString = String.valueOf(bigInt);
        System.out.println(bigInt);
        //int i =  3289650;
        String hex =  Integer.toHexString(Integer.valueOf(bigIntString));
        int iii = Integer.valueOf(bigIntString);
        System.out.println("hex for " + bigIntString + " = " + Integer.toHexString(iii));
        //Color color_from_rgb = new Color(Integer.valueOf(hex));
        Color color_from_rgb = new Color(Integer.valueOf(rgb));
        System.out.println("getRed = " + color_from_rgb.getRed());
        System.out.println("getBlue = " + color_from_rgb.getBlue());
        System.out.println("getGreen = " + color_from_rgb.getGreen());
        hexa(iii);    */

        //ColorBase cb = new ColorBase();
        //System.out.println(cb.getColorName("8B0000"));
        int[][] im_arr_color = convertTo2DUsingGetRGB(image);
        //int[][] im_arr_grey = new int[im_arr_color.length][im_arr_color.length];

        BufferedImage image_grey = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<image_grey.getWidth(); ++i) {
            for (int j=0; j<image_grey.getHeight(); ++j) {
                image_grey.setRGB(i,j, convertToGrey(im_arr_color[i][j]));
            }
        }
        File saveFile = new File("savedimage_grey2.jpg");
        ImageIO.write(image_grey, "jpg", saveFile);

    }

    public static void hexa(int num) {
        int m = 0;
        if( (m = num >>> 4) != 0 ) {
            hexa( m );
        }
        System.out.print((char)((m=num & 0x0F)+(m<10 ? 48 : 55)));
    }

    public static void print(int[][] res) {
        for (int i=0; i<res.length; ++i) {
            for (int j=0; j<res[i].length; ++j) {
                System.out.print(res[i][j] + " ; ");
            }
            System.out.println();
        }
    }

    public static int convertToGrey(int rgb) {
        Color color = new Color(rgb);
        int value = (int)Math.round(0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());
        Color c = new Color(value, value, value);
        return c.getRGB();
        //return (int)Math.round(0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());
        //return (color.getRed()+color.getGreen()+color.getBlue())/3;
    }

    public static int convertToColor(int rgb) {
        Color color = new Color(rgb);
        int value = (int)Math.round(0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());
        Color c = new Color(value, value, value);
        return c.getRGB();
        //return (int)Math.round(0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());
        //return (color.getRed()+color.getGreen()+color.getBlue())/3;
        //return rgb;
    }

    public static int convert(int n) {
        return Integer.valueOf(String.valueOf(n), 16);
    }

    public static BigInteger getBigIntFromRGB(int r, int g, int b) {
        String s = String.valueOf((r*65536)+(g*256)+b);
        return new BigInteger(s);
    }

    private static int[][] convertTo2DUsingGetRGBSite(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = image.getRGB(row, col);
            }
        }

        return result;
    }

    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        System.out.println("width = " + width);
        int height = image.getHeight();
        System.out.println("height = " + height);
        int[][] result = new int[width][height];
        System.out.println("result.length = " + result.length);


        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                result[x][y] = image.getRGB(x, y);
            }
        }

        return result;
    }

    private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

}
