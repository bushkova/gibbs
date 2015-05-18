import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

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
        //getDistancesBetweenPixels(im_arr_color, image.getWidth(), image.getHeight());
        //int[][] im_arr_grey = new int[im_arr_color.length][im_arr_color.length];

        /*BufferedImage image_grey = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<image_grey.getWidth(); ++i) {
            for (int j=0; j<image_grey.getHeight(); ++j) {
                image_grey.setRGB(i,j, convertToGrey(im_arr_color[i][j]));
            }
        }
        File saveFile = new File("savedimage_grey2.jpg");
        ImageIO.write(image_grey, "jpg", saveFile);  */

        int height = image.getHeight();
        int width = image.getWidth();

        int thisColor, up, down, left, right;
        /*for (int i=1; i<=height-2; ++i) {
            for (int j=1; j<=width-2; ++j) {
                thisColor = im_arr_color[j][i];
                Color c = new Color(thisColor);
                up = im_arr_color[j][i-1];
                down = im_arr_color[j][i+1];
                left = im_arr_color[j-1][i];
                right = im_arr_color[j+1][i];
                Color upC = new Color(up);
                Color downC = new Color(down);
                Color leftC = new Color(left);
                Color rightC = new Color(right);
                //System.out.println("["+j+"]["+i+"]: "+(thisColor-up)+", "+(thisColor-down)+", "+(thisColor-left)+", "+(thisColor-right));
                System.out.println("["+j+"]["+i+"]: "+c.getRed()+"-"+c.getGreen()+"-"+c.getBlue()+", "
                        +upC.getRed()+"-"+upC.getGreen()+"-"+upC.getBlue()+", "
                        +downC.getRed()+"-"+downC.getGreen()+"-"+downC.getBlue()+", "
                        +leftC.getRed()+"-"+leftC.getGreen()+"-"+leftC.getBlue()+", "
                        +rightC.getRed()+"-"+rightC.getGreen()+"-"+rightC.getBlue()+", ");
                System.out.println("["+j+"]["+i+"]: "+Math.abs(c.getRed()-upC.getRed())+"-"+Math.abs(c.getGreen()-upC.getGreen())+"-"+Math.abs(c.getBlue()-upC.getBlue())+", "
                        +Math.abs(c.getRed()-downC.getRed())+"-"+Math.abs(c.getGreen()-downC.getGreen())+"-"+Math.abs(c.getBlue()-downC.getBlue())+", "
                        +Math.abs(c.getRed()-leftC.getRed())+"-"+Math.abs(c.getGreen()-leftC.getGreen())+"-"+Math.abs(c.getBlue()-leftC.getBlue()) +", "
                        +Math.abs(c.getRed()-rightC.getRed())+"-"+Math.abs(c.getGreen()-rightC.getGreen())+"-"+Math.abs(c.getBlue()-rightC.getBlue())+", "
                                +(Math.abs(c.getRed()-upC.getRed())+Math.abs(c.getGreen()-upC.getGreen())+Math.abs(c.getBlue()-upC.getBlue()))/3 + ", "
                                +(Math.abs(c.getRed()-downC.getRed())+Math.abs(c.getGreen()-downC.getGreen())+Math.abs(c.getBlue()-downC.getBlue()))/3 + ", "
                                +(Math.abs(c.getRed()-leftC.getRed())+Math.abs(c.getGreen()-leftC.getGreen())+Math.abs(c.getBlue()-leftC.getBlue()))/3 + ", "
                                +(Math.abs(c.getRed()-rightC.getRed())+Math.abs(c.getGreen()-rightC.getGreen())+Math.abs(c.getBlue()-rightC.getBlue()))/3 + ", "
                );
            }
        }   */

        /*BufferedImage small_part = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<3; ++i) {
            for (int j=0; j<3; ++j) {
                small_part.setRGB(i,j, im_arr_color[i+75][j+78]);
            }
        }
        File saveFile = new File("small_part.jpg");
        ImageIO.write(small_part, "jpg", saveFile);     */

        BufferedImage salt_and_pepper = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<width; ++i) {
            for (int j=0; j<height; ++j) {
                salt_and_pepper.setRGB(i,j, im_arr_color[i][j]);
            }
        }
        Random r = new Random();
        int color = 16777215;
        //salt
        for (int i=0; i<1000; ++i) {
            int on_width = r.nextInt(2555);
            int on_height = r.nextInt(1595);
            //for (int j=0; j<20; ++j) {
                //for (int k=0; k<20; ++k) {
                    //salt_and_pepper.setRGB(on_width+k, on_height + j, 16777215);
                salt_and_pepper.setRGB(on_width, on_height , color);
                salt_and_pepper.setRGB(on_width + 1, on_height , color);
                salt_and_pepper.setRGB(on_width + 2, on_height , color);
                salt_and_pepper.setRGB(on_width, on_height + 1, color);
                salt_and_pepper.setRGB(on_width + 1, on_height + 1, color);
                salt_and_pepper.setRGB(on_width + 2, on_height + 1, color);
                salt_and_pepper.setRGB(on_width, on_height + 2, color);
                salt_and_pepper.setRGB(on_width + 1, on_height + 2, color);
                salt_and_pepper.setRGB(on_width + 2, on_height + 2, color);
                salt_and_pepper.setRGB(on_width, on_height + 3, color);
                salt_and_pepper.setRGB(on_width + 1, on_height + 3, color);
                salt_and_pepper.setRGB(on_width + 2, on_height + 3, color);
                }
            //}
       // }
        //pepper
        for (int i=0; i<1000; ++i) {
            color = 0;
            int on_width = r.nextInt(2555);
            int on_height = r.nextInt(1595);
            //for (int j=0; j<20; ++j) {
            //for (int k=0; k<20; ++k) {
            //salt_and_pepper.setRGB(on_width+k, on_height + j, 16777215);
            salt_and_pepper.setRGB(on_width, on_height , color);
            salt_and_pepper.setRGB(on_width + 1, on_height , color);
            salt_and_pepper.setRGB(on_width + 2, on_height , color);
            salt_and_pepper.setRGB(on_width, on_height + 1, color);
            salt_and_pepper.setRGB(on_width + 1, on_height + 1, color);
            salt_and_pepper.setRGB(on_width + 2, on_height + 1, color);
            salt_and_pepper.setRGB(on_width, on_height + 2, color);
            salt_and_pepper.setRGB(on_width + 1, on_height + 2, color);
            salt_and_pepper.setRGB(on_width + 2, on_height + 2, color);
            salt_and_pepper.setRGB(on_width, on_height + 3, color);
            salt_and_pepper.setRGB(on_width + 1, on_height + 3, color);
            salt_and_pepper.setRGB(on_width + 2, on_height + 3, color);
        }
        File saveFile = new File("salt_and_pepper.jpg");
        ImageIO.write(salt_and_pepper, "jpg", saveFile);

    }

    public static double[] getDistancesBetweenPixels(int[][] input, int width, int height) {
        System.out.println("[0][0] = " + input[0][0]);
        System.out.println("[159][99] = " + input[159][99]);
        int thisColor, up, down, left, right;
        for (int i=1; i<=height-2; ++i) {
            for (int j=1; j<=width-2; ++j) {
                thisColor = input[j][i];
                Color c = new Color(thisColor);
                up = input[j][i-1];
                down = input[j][i+1];
                left = input[j-1][i];
                right = input[j+1][i];
                //System.out.println("["+j+"]["+i+"]: "+(thisColor-up)+", "+(thisColor-down)+", "+(thisColor-left)+", "+(thisColor-right));
                System.out.println("["+j+"]["+i+"]: "+c.getRed()+"-"+c.getGreen()+"-"+c.getBlue()+", "+up+", "+down+", "+left+", "+right);
            }
        }

        return null;
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
