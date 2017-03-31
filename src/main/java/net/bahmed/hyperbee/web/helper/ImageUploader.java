package net.bahmed.hyperbee.web.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author duity
 * @since 11/28/16.
 */
@Component
public class ImageUploader {
    private static final String IMAGES = "Images";
    private static final String TOMCAT_HOME_PROPERTY = "user.home";
    private static final String TOMCAT_HOME_PATH = System.getProperty(TOMCAT_HOME_PROPERTY);
    private static final String IMAGES_PATH = TOMCAT_HOME_PATH + File.separator + IMAGES;

    private static final File IMAGES_DIR = new File(IMAGES_PATH);
    private static final String IMAGES_DIR_ABSOLUTE_PATH = IMAGES_DIR.getAbsolutePath() + File.separator;

    private static final String FAILED_UPLOAD_MESSAGE = "You failed to upload [%s] because the file because %s";
    private static final String SUCCESS_UPLOAD_MESSAGE = "You successfully uploaded file = [%s]";

    public void createImagesDirIfNeeded() {
        if (!IMAGES_DIR.exists()) {
            IMAGES_DIR.mkdirs();
        }
    }

    public String createImage(String name, MultipartFile file) {
        try {
            File image = new File(IMAGES_DIR_ABSOLUTE_PATH + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
            stream.write(file.getBytes());
            stream.close();

            return String.format(SUCCESS_UPLOAD_MESSAGE, name);
        } catch (Exception e) {
            return String.format(FAILED_UPLOAD_MESSAGE, name, e.getMessage());
        }
    }

    public String getImagesDirAbsolutePath() {
        return IMAGES_DIR_ABSOLUTE_PATH;
    }
}
