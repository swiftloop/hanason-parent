package com.framework.hanason.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * @author sorata
 * @date 2019-08-09 15:16
 */
public class ImageUtils {

    private ImageUtils() {
    }


    public static BufferedImage copy(InputStream inputStream, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(inputStream);
            BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());
            Graphics graphics = bufferedImage.getGraphics();
            graphics.drawImage(image, 0, 0, width, height, null);
            graphics.dispose();
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.autoClose(inputStream);
        }
        return null;
    }

    public static void save(BufferedImage bufferedImage, String format, String out) throws IOException {
        ImageIO.write(bufferedImage, format, new FileOutputStream(out));
    }


    public static void save(BufferedImage bufferedImage, String format, OutputStream outputStream) throws IOException {
        ImageIO.write(bufferedImage, format, outputStream);
    }

    /**
     * 增加图片的水印  具体的样式自行修改 此为样例
     *
     * @param bufferedImage 图片
     * @param x             位置x
     * @param y             位置y
     * @param waterMark     水印的内容
     */
    public static void waterMark(BufferedImage bufferedImage, int x, int y, String waterMark) {
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("Default", Font.BOLD, 50));
        graphics.setColor(Color.pink);
        graphics.drawString(waterMark, x, y);
        graphics.dispose();
    }

    public static BufferedImage codeImage(char[] chars, int imageWidth, int imageHeight) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(Color.black);
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            int x = random.nextInt(imageWidth - 2) + 1;
            int y = random.nextInt(imageHeight - 2) + 1;
            int mx = random.nextInt(1);
            int my = random.nextInt(1);
            graphics.drawLine(x, y, x + mx, y + my);
        }
        graphics.setFont(new Font("Default", Font.BOLD | Font.ITALIC, 24));
        graphics.setColor(Color.gray);

        int length = chars.length;
        int offset = imageWidth / length;
        int h = imageHeight / 2 + 10;
        for (int i = 0; i < length; i++) {
            graphics.drawString(String.valueOf(chars[i]), (i * offset) + (offset / 2 - 10), h);
        }
        return image;
    }

    public static void main(String[] args) throws IOException {
        ImageIO.write(codeImage(new char[]{'c','e','2','3'},200,30),"jpg",
                new FileOutputStream(new File("/Users/cocos/Desktop/cert/a.jpg")));
    }


}
