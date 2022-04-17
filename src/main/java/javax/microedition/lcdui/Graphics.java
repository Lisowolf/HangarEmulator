package javax.microedition.lcdui;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import things.ImageUtils;

import javax.microedition.lcdui.game.Sprite;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphics {
    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;
    public static final int SOLID = 0;
    public static final int DOTTED = 1;

    private final java.awt.Graphics graphics;
    private java.awt.Font font;
    private int translateX = 0, translateY = 0;

    public Graphics(java.awt.Graphics graphics) {
        this.graphics = graphics;
    }

    public void translate(int x, int y) {
        graphics.translate(x, y);
        translateX += x;
        translateY += y;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void setColor(int red, int green, int blue) {
        graphics.setColor(new Color(red, green, blue));
    }

    public void setColor(int RGB) {
        graphics.setColor(new Color(RGB));
    }

    public Font getFont() {
        return new Font(font);
    }

    public void setStrokeStyle(int style) throws NotImplementedException {
        // TODO: write method logic
        throw new NotImplementedException("setStrokeStyle");
    }

    public int getStrokeStyle() {
        return font.getStyle();
    }

    public void setFont(Font font) {
        this.font = new java.awt.Font(java.awt.Font.SANS_SERIF, font.getStyle(), font.getSize());
    }

    public int getClipX() {
        return graphics.getClipBounds().x;
    }

    public int getClipY() {
        return graphics.getClipBounds().y;
    }

    public int getClipWidth() {
        return graphics.getClipBounds().width;
    }

    public int getClipHeight() {
        return graphics.getClipBounds().height;
    }

    public void clipRect(int x, int y, int width, int height) {
        graphics.clipRect(x, y, width, height);
    }

    public void setClip(int x, int y, int width, int height) {
        graphics.setClip(x, y, width, height);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(x, y, width, height);
    }

    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        graphics.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        graphics.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawString(String str, int x, int y, int anchor) {
        // TODO: write text align logic
        graphics.drawString(str, x, y);
    }

    public void drawSubstring(String str, int offset, int len, int x, int y, int anchor) throws NotImplementedException {
        // TODO: rewrite method logic
        drawString(str, x, y, anchor);
    }

    public void drawChar(char character, int x, int y, int anchor) throws NotImplementedException {
        // TODO: write method logic
        throw new NotImplementedException("drawChar");
    }

    public void drawChars(char[] data, int offset, int length, int x, int y, int anchor) throws NotImplementedException {
        // TODO: write method logic
        throw new NotImplementedException("drawChars");
    }

    public void drawImage(Image img, int x, int y, int anchor) {
        x = ImageUtils.AlignX(img.getWidth(), x, anchor);
        y = ImageUtils.AlignY(img.getHeight(), y, anchor);
        graphics.drawImage(img.image, x, y, null);
    }

    public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) throws NotImplementedException {
        BufferedImage bufferedImage = (BufferedImage) src.image;
        BufferedImage subImage = bufferedImage.getSubimage(x_src, y_src, width, height);

        x_dest = ImageUtils.AlignX(subImage.getWidth(), x_dest, anchor);
        y_dest = ImageUtils.AlignY(subImage.getHeight(), y_dest, anchor);

        switch (transform) {
            case Sprite.TRANS_MIRROR:
                x_dest += width;
                width = -width;
                break;
            default:
                // TODO: write logic for rotation
                throw new NotImplementedException("drawRegion");
        }
        graphics.drawImage(subImage, x_dest, y_dest, width, height, null);
    }

    public void copyArea(int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor) {
        x_dest = ImageUtils.AlignX(width, x_dest, anchor);
        y_dest = ImageUtils.AlignY(height, y_dest, anchor);
        graphics.copyArea(x_src, y_src, width, height, x_dest, y_dest);
    }

    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        int[] xPoints = new int[] { x1, x2, x3 };
        int[] yPoints = new int[] { y1, y2, y3 };
        graphics.fillPolygon(xPoints, yPoints, 3);
    }

    public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) throws NotImplementedException {
        // TODO: write method logic
        throw new NotImplementedException("drawRGB");
    }

    public int getDisplayColor(int color) {
        // TODO: it is correct?
        return color;
    }
}