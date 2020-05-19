package com.framework.hanason.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author sorata
 * @date 2019-08-09 15:19
 */
public class IOUtils {

    private IOUtils(){}
    private static final Logger LOGGER = LoggerFactory.getLogger("IOUtils");
    private static final boolean DEBUG = true;


    public static byte[] readAll(InputStream inputStream) throws IOException {
        return outputStream(inputStream).toByteArray();
    }

    public static ByteArrayOutputStream outputStream(InputStream inputStream) throws IOException {
        Objects.requireNonNull(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int i = inputStream.read(); i >=0; i = inputStream.read()){
            outputStream.write(i);
        }
        return outputStream;
    }




    public static void autoClose(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    //
                    if (DEBUG) {
                        LOGGER.error("close file error", e);
                    }
                }
            }
        }
    }



}
