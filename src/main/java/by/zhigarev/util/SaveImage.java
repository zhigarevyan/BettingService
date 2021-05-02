package by.zhigarev.util;

import javax.servlet.http.Part;
import java.io.*;

public class SaveImage {

    private static final String PROJECT_PATH_TOMCAT = "C:/kiki/programs/apache-tomcat-9.0.44/webapps/BettingService/";
    private static final String PROJECT_PATH = "C:/kiki/epam/JAVA/web/";
    private static final String IMG_FOLDER_PATH = "img/";
    private static final String FILENAME_EXTENSION = ".jpg";
    private static final String SIGN_UNDERSCORE = "_";

    public String addImage(Part inputFile, String name, String surname) throws IOException {
        final String IMAGE_LOCATION = IMG_FOLDER_PATH + name + SIGN_UNDERSCORE + surname + FILENAME_EXTENSION;

        InputStream inputStream;
        OutputStream outStream;
        OutputStream outStreamTomcat;

        inputStream = inputFile.getInputStream();

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        File imageFile = new File(PROJECT_PATH + IMAGE_LOCATION);
        File imageFileTomcat = new File(PROJECT_PATH_TOMCAT + IMAGE_LOCATION);

        if (!imageFile.exists()) {
            imageFile.createNewFile();

        }
        if (!imageFileTomcat.exists()) {
            imageFileTomcat.createNewFile();

        }
        outStream = new FileOutputStream(imageFile);
        outStream.write(buffer);
        outStream.close();
        outStreamTomcat = new FileOutputStream(imageFileTomcat);
        outStreamTomcat.write(buffer);
        outStreamTomcat.close();
        return IMAGE_LOCATION;
    }
}

