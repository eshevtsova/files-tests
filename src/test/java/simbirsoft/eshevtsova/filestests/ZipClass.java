package simbirsoft.eshevtsova.filestests;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.requireNonNull;

public class ZipClass {

    public static String readZip(String zipName, String fileName, String password) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(requireNonNull(classLoader.getResource(zipName)).toURI());
        ZipFile zipFile = new ZipFile(file, password.toCharArray());
        FileHeader fileHeader = zipFile.getFileHeader(fileName);
        InputStream inputStream = zipFile.getInputStream(fileHeader);
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}
