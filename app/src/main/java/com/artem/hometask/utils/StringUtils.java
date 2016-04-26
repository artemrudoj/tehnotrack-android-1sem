package com.artem.hometask.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by artem on 22.04.16.
 */
public class StringUtils {
    public static String readInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[16384];

        while ((read = is.read(data, 0, data.length)) != -1) {
            outputStream.write(data, 0, read);
        }

        outputStream.flush();
        return outputStream.toString("utf-8");
    }
}
