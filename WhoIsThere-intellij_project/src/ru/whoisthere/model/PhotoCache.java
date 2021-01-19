package ru.whoisthere.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static ru.whoisthere.utils.Logging.addWarningLog;

public class PhotoCache {
    private static List<Person> personsCache = new ArrayList<>();
    private static boolean isEmpty = true;

    public static boolean isEmpty() {
        return isEmpty;
    }

    public static void load(boolean isEmpty) {
        PhotoCache.isEmpty = isEmpty;
    }

    public static void addPersonToCache(Person person) {
        personsCache.remove(person);
        personsCache.add(person);
    }

    public static Person getPersonFromCache(Person person) {
        for (Person p : personsCache) {
            if (p.equals(person)) {
                return p;
            }
        }
        return new Person("NotFound", "", "", "", new BufferedImage(100, 100, TYPE_INT_RGB));
    }

    public static boolean personsCacheContains(Person person) {
        return personsCache.contains(person);
    }

    public static BufferedImage biToImage(byte[] ph) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(ph));
            double imgWidth = img.getWidth();
            double imgHeight = img.getHeight();
            double imgRatio = imgHeight / imgWidth;
            img = resize(img, (int) (100 * imgRatio), 100);
        } catch (IOException e) {
            addWarningLog(e.getMessage() + "error read image");
        } catch (NullPointerException e) {
            addWarningLog(e.getMessage() + "no photo in database");
            return new BufferedImage(100, 100, TYPE_INT_RGB);
        }
        return img;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        java.awt.Image tmp = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
