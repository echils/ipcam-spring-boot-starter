package com.github.ipcam.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.github.ipcam.utils.FileUtils.createParentDirectory;

/**
 * GraphicsUtils
 *
 * @author echils
 */
public class GraphicsUtils {


    private static final Logger logger = LoggerFactory.getLogger(GraphicsUtils.class);

    // Minimum size of image width and height
    private static final int IMAGE_MIN_MEASURE = 506;

    // Maximum size of image width and height
    private static final int IMAGE_MAX_MEASURE = 1920;


    /**
     * Create a new picture by source picture
     *
     * @param sourceFile Source picture
     * @param newWidth   The width of the new picture
     * @param newHeight  The height of the new picture
     * @param targetPath The output path of the new picture
     */
    public static boolean createNewImage(File sourceFile, int newWidth, int newHeight, String targetPath) {
        if (sourceFile == null) {
            return false;
        }

        String fileName = sourceFile.getPath();
        if ("".equals(fileName) || fileName.lastIndexOf(".") == -1) {
            return false;
        }

        if (targetPath == null || "".equals(targetPath) || targetPath.lastIndexOf(".") == -1) {
            return false;
        }

        createParentDirectory(targetPath);
        newWidth = Math.max(newWidth, IMAGE_MIN_MEASURE);
        newWidth = Math.min(newWidth, IMAGE_MAX_MEASURE);
        newHeight = Math.max(newHeight, IMAGE_MIN_MEASURE);
        newHeight = Math.min(newHeight, IMAGE_MAX_MEASURE);

        String fileSuffix = fileName.substring((fileName.lastIndexOf(".") + 1));
        if (!sourceFile.exists() || !sourceFile.isAbsolute() || !sourceFile.isFile()
                || !checkImageFileSupport(fileSuffix)) {
            return false;
        }
        try (FileOutputStream out = new FileOutputStream(targetPath)) {
            Image src = ImageIO.read(sourceFile);
            BufferedImage targetImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            targetImage.getGraphics().drawImage(src, 0, 0, newWidth, newHeight, null);
            ImageIO.write(targetImage, fileSuffix, out);
        } catch (IOException e) {
            logger.error("Create new image filed:{}", e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean checkImageFileSupport(String suffix) {
        return "jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix);
    }


}
